// FIX: Add parentheses to elvis right-hand side
fun test(i: Int?, b: Boolean?) {
    val x = b /* a */ <caret>?: /* b */ i /* c */ == /* d */ null
}