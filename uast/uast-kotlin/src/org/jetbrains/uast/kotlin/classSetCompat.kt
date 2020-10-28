/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.uast.kotlin

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


interface ClassSet<out T> {
    fun isEmpty(): Boolean
    operator fun contains(element: Class<out @UnsafeVariance T>): Boolean
}

fun <T> T?.isInstanceOf(classSet: ClassSet<T>): Boolean =
    this?.let { classSet.contains(it.javaClass) } ?: false

fun <T> ClassSet<T>.hasClassOf(instance: T?): Boolean =
    instance?.let { contains(it.javaClass) } ?: false

private class ClassSetImpl<out T>(vararg val initialClasses: Class<out T>) : ClassSet<T> {

    private val isSimple = initialClasses.size <= SIMPLE_CLASS_SET_LIMIT

    private lateinit var internalMapping: ConcurrentMap<Class<out T>, Boolean>

    init {
        if (!isSimple)
            internalMapping = ConcurrentHashMap<Class<out T>, Boolean>().apply {
                for (initialClass in initialClasses)
                    this[initialClass] = true
            }
    }

    override fun isEmpty(): Boolean = initialClasses.isEmpty()

    override operator fun contains(element: Class<out @UnsafeVariance T>): Boolean =
        if (isSimple)
            initialClasses.any { it.isAssignableFrom(element) }
        else
            internalMapping[element]
                ?: initialClasses.any { it.isAssignableFrom(element) }.also { internalMapping[element] = it }
}

fun <T> classSetOf(vararg classes: Class<out T>): ClassSet<T> =
    if (classes.isNotEmpty()) ClassSetImpl(*classes) else emptyClassSet

private val emptyClassSet: ClassSet<Nothing> = object : ClassSet<Nothing> {
    override fun isEmpty() = true
    override fun contains(element: Class<out Nothing>): Boolean = false
}

fun <T> emptyClassSet(): ClassSet<T> = emptyClassSet

class ClassSetsWrapper<out T>(val sets: Array<ClassSet<@UnsafeVariance T>>) : ClassSet<T> {
    override fun isEmpty(): Boolean = sets.all { it.isEmpty() }
    override operator fun contains(element: Class<out @UnsafeVariance T>): Boolean = sets.any { it.contains(element) }
}

private const val SIMPLE_CLASS_SET_LIMIT = 5