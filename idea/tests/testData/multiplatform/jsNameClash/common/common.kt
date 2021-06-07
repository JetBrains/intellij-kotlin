package sample

expect interface <!LINE_MARKER("descr='Has actuals in JS'"), LINE_MARKER("descr='Is subclassed by AbstractInput JSInput'")!>Input<!>

abstract class <!LINE_MARKER("descr='Is subclassed by JSInput'")!>AbstractInput<!> : Input {
    val head: Int = null!!
}
