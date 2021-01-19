// PROBLEM: none

fun println(s: String) {}

fun foo(size: Int, i: Int) {
    for (i in 1..size) {
        <caret>if (i == 1) {
            break
        }
        else if (i == 2) {
            println("$i")
        }
        else {
            println("*")
        }
    }
}