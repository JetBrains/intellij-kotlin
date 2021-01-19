// PROBLEM: none

fun println(s: String) {}

fun test(i: Int) {
    if (i == 1) {
        println("a")
    }
    else <caret>if (i == 2) {
        println("b")
    }
    else {
        println("none")
    }
}