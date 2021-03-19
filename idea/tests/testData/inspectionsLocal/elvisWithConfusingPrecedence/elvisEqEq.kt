// FIX: Add parentheses to elvis expression
fun test(i: Int?, b: Boolean?) {
    val x = b <caret>?: i == null
}