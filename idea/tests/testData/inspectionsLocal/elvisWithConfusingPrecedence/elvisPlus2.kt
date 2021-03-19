// FIX: Add parentheses to elvis right-hand side
fun test(i: Int?) {
    val y = i <caret>?: 0 + 1
}