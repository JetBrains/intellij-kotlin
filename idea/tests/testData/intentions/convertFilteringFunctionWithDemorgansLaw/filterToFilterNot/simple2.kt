// WITH_RUNTIME
fun test() {
    val x = listOf(1, 2, 3, 4, 5).filter<caret> { !foo(it) || !bar(it) }
}

fun foo(i: Int) = true
fun bar(i: Int) = true