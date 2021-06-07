// Copyright 2000-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package sample

actual interface <!LINE_MARKER("descr='Has declaration in common module'"), LINE_MARKER("descr='Is implemented by CommonMainImpl [jvm]'")!>CommonMainInterface<!> {
    actual val <!LINE_MARKER("descr='Has declaration in common module'"), LINE_MARKER("descr='Is implemented in sample.CommonMainImpl'")!>propertyFromInterface<!>: Int
}

actual class <!LINE_MARKER("descr='Has declaration in common module'")!>CommonMainImpl<!> : CommonMainInterface {
    override val <!LINE_MARKER("descr='Implements property in 'CommonMainInterface''")!>propertyFromInterface<!>: Int = 42
    actual val <!LINE_MARKER("descr='Has declaration in common module'")!>propertyFromImpl<!>: Int = 42
}
