// PROBLEM: none

fun println(s: String) {}

fun foo(i: Int, b: Boolean) {
    <caret>if (i == 1) {
        if (b) println("ab")
        else println("a")
    }
    else if (i == 2) {
        println("b")
    }
    else {
        println("none")
    }
}