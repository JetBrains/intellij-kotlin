// FIX: Add parentheses to elvis right-hand side
fun test(i: Int?, b: Boolean?) {
    val x = b
        <caret>?: // comment
        i == null
}