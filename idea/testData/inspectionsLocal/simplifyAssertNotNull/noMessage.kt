// FIX: "Replace with '!!' operator"
// WITH_RUNTIME

fun foo(p: Array<String?>) {
    val v = p[0]
    <caret>assert(v != null)
}