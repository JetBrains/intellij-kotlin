// "Remove annotation" "true"
// COMPILER_ARGUMENTS: -Xopt-in=kotlin.RequiresOptIn
// WITH_RUNTIME

@RequiresOptIn
@Target(AnnotationTarget.LOCAL_VARIABLE)
annotation class SomeOptInAnnotation

fun foo() {
    <caret>@SomeOptInAnnotation
    var x: Int = 0
}
