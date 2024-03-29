// "Opt in for 'MyExperimentalAPI' on 'bar'" "true"
// PRIORITY: HIGH
// COMPILER_ARGUMENTS: -Xopt-in=kotlin.RequiresOptIn
// WITH_RUNTIME

package a.b

@RequiresOptIn
@Target(AnnotationTarget.CLASS)
annotation class MyExperimentalAPI

@MyExperimentalAPI
class Some

class Bar {
    fun bar() {
        <caret>Some()
    }
}
