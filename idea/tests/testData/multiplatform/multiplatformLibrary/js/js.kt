package sample

actual class <!LINE_MARKER("descr='Has expects in common module'")!>Sample<!> {
    actual fun <!LINE_MARKER("descr='Has expects in common module'")!>checkMe<!>() = 12
}

actual object <!LINE_MARKER("descr='Has expects in common module'")!>Platform<!> {
    actual val <!LINE_MARKER("descr='Has expects in common module'")!>name<!>: String = "JS"
}
