// "Create expected class in common module testModule_Common" "true"
// DISABLE-ERRORS

actual class <caret>Some {
    actual companion object {
        actual fun createMe() = Some()
    }
}