package com.h0tk3y.hmpp.klib.demo

interface <!LINE_MARKER("descr='Is implemented by LibCommonMainExpect [lib-and-app.lib.commonMain] LibCommonMainExpect [lib-and-app.lib.iosMain] LibCommonMainExpect [lib-and-app.lib.jvmAndJsMain]  Click or press ... to navigate'")!>LibCommonMainInterface<!>

expect class <!LINE_MARKER!>LibCommonMainExpect<!> constructor() : LibCommonMainInterface {
    fun <!LINE_MARKER!>libCommonMainExpectFun<!>()
}

fun libCommonMainTopLevelFun(): Int {
    println("commonMainTopLevelFun")
    return 0
}

fun main() {
    println(LibCommonMainExpect())
    println(libCommonMainTopLevelFun())
}