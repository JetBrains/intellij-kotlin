// PROBLEM: none
class Test {
    <caret>inner class Inner

    fun foo(t: Test) {
        t.Inner()
    }
}