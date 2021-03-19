// FIX: Add parentheses to elvis expression
fun test(i: Int?) {
    val x = i
        <caret>?:
        0 + 1
}