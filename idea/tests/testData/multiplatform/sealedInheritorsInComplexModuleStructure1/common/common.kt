package foo

expect sealed class <!LINE_MARKER("descr='Has actuals in JVM'"), LINE_MARKER("descr='Is subclassed by SealedWithPlatformActuals [common] SealedWithPlatformActuals [main] SimpleShared'")!>SealedWithSharedActual<!>()
expect sealed class <!LINE_MARKER("descr='Is subclassed by SimpleShared'")!>SealedWithPlatformActuals<!> : SealedWithSharedActual
