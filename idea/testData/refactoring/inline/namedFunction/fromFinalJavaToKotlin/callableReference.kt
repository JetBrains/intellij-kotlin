// ERROR: Inline of Java method is not supported for Kotlin

import kotlin.reflect.KFunction

fun takeKFunction(par: KFunction<String>): String = par.name
fun check() {
    takeKFunction(Test::returnStri<caret>ng)
}