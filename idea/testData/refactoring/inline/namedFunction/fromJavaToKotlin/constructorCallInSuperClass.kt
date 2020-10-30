// ERROR: Inline of Java method is not supported for Kotlin

class KotlinClass : JavaClass<caret>() {

    fun someF() = "kotlin fun"

}