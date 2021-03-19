// FIX: Add parentheses to elvis expression
fun test(i: Int?) {
    val x = i /* a */ <caret>?: /* b */ 0 /* c */ + /* d */ 1
}