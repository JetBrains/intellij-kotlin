// "Replace with 'newFun(p2, p1, handler)'" "true"

interface I {
    @Deprecated("", ReplaceWith("newFun(p2, p1, handler)"))
    fun oldFun(p1: String = "", p2: Int = 0, handler: () -> Unit)

    fun newFun(a: Int = 0, b: String = "", handler: () -> Unit)
}

fun foo(i: I) {
    i.<caret>oldFun { }
}
