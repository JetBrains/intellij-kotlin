// WITH_RUNTIME

fun bar(f: () -> Unit) {}
fun bar(g: (Int, Int) -> Unit) {}

fun test() {
    bar(f = { -><caret> })
}