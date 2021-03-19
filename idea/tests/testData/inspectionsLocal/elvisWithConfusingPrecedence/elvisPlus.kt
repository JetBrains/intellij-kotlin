// FIX: Add parentheses to elvis expression
fun test(i: Int?) {
    val y = i <caret>?: 0 + 1
}