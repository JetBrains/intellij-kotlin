// "Add '-Xopt-in=test.MyExperimentalAPI' to module light_idea_test_case compiler arguments" "true"
// PRIORITY: LOW
// COMPILER_ARGUMENTS: -Xopt-in=kotlin.RequiresOptIn
// COMPILER_ARGUMENTS_AFTER: -Xopt-in=kotlin.RequiresOptIn -Xopt-in=test.MyExperimentalAPI
// DISABLE-ERRORS
// WITH_RUNTIME

package test

@RequiresOptIn
annotation class MyExperimentalAPI

@MyExperimentalAPI
class Some

class Bar {
    fun bar() {
        <caret>Some()
    }
}
