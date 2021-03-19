// PROBLEM: none
fun test(i: Int?, b: Boolean?) {
    val x = null == b <caret>?: i
}