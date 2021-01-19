// PROBLEM: none

fun println(s: String) {}

fun test(i: Int) {
    <caret>if (i == 1) {
        println("a")
    }
    else if (i == 2) {
        println("b")
    }
}