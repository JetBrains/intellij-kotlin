// "Create expected class in common module testModule_Common" "true"
// DISABLE-ERRORS

actual class A<T> {
    actual fun <caret>a(): T = TODO()
}