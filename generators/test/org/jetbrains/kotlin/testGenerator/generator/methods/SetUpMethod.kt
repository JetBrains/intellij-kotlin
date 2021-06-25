package org.jetbrains.kotlin.testGenerator.generator.methods

import org.jetbrains.kotlin.testGenerator.generator.*
import org.jetbrains.kotlin.testGenerator.model.TAnnotation

class SetUpMethod(private val codeLines: List<String>) : TestMethod {
    override val methodName = "setUp"

    override fun Code.render() {
        appendAnnotation(TAnnotation<Override>())
        appendBlock("protected void $methodName()") {
            codeLines.forEach { appendLine(it) }
            append("super.setUp();")
        }
    }
}