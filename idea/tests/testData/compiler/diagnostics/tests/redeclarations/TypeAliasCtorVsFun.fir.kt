// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -TOPLEVEL_TYPEALIASES_ONLY
class C(val x: Int)

typealias CC = C

fun CC(x: Int) = x

class Outer {
    class C(val x: Int)

    typealias CC = C

    fun CC(x: Int) = x
}
