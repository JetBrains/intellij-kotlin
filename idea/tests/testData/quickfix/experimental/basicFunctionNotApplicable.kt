// "Add '@MyExperimentalAPI' annotation to 'bar'" "false"
// COMPILER_ARGUMENTS: -Xopt-in=kotlin.RequiresOptIn
// WITH_RUNTIME
// ERROR: This declaration is experimental and its usage must be marked with '@MyExperimentalAPI' or '@OptIn(MyExperimentalAPI::class)'

@RequiresOptIn
@Target(AnnotationTarget.CLASS)
annotation class MyExperimentalAPI

@MyExperimentalAPI
class Some {
    fun foo() {}
}

class Bar {
    fun bar() {
        Some().foo<caret>() // No error on foo since KT-48570
    }
}
