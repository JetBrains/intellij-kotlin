// FIX: Add parentheses to elvis expression
fun test(i: Int?, b: Boolean?) {
    val x = b /* a */ <caret>?: /* b */ i /* c */ == /* d */ null
}