// Copyright 2000-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package sample

expect interface <!LINE_MARKER("descr='Has actuals in JVM'"), LINE_MARKER("descr='Is subclassed by CommonMainImpl [common] CommonMainImpl [jvm]  Click or press ... to navigate'")!>CommonMainInterface<!> {
    val <!LINE_MARKER("descr='Has actuals in JVM'"), LINE_MARKER("descr='Is implemented in sample.CommonMainImpl'")!>propertyFromInterface<!>: Int
}

expect class <!LINE_MARKER("descr='Has actuals in JVM'")!>CommonMainImpl<!> : CommonMainInterface {
    val <!LINE_MARKER("descr='Has actuals in JVM'")!>propertyFromImpl<!>: Int
}
