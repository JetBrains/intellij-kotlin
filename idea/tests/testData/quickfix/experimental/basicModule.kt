// "Add '-opt-in=test.MyExperimentalAPI' to module light_idea_test_case compiler arguments" "true"
// PRIORITY: LOW
// COMPILER_ARGUMENTS: -opt-in=kotlin.RequiresOptIn
// COMPILER_ARGUMENTS_AFTER: -opt-in=kotlin.RequiresOptIn -opt-in=test.MyExperimentalAPI
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
