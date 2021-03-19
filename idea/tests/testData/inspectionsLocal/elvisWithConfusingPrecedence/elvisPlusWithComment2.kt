// FIX: Add parentheses to elvis right-hand side
fun test(i: Int?) {
    val x = i /* a */ <caret>?: /* b */ 0 /* c */ + /* d */ 1
}