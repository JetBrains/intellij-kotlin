data class Foo(val a: String, val b: String, val c: String)

fun bar(f: Foo) {
    val (/* comment a */ a, /* comment b */ b<caret>) = f // comment
}
