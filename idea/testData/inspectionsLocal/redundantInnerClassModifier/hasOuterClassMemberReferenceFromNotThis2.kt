class Test {
    <caret>inner class Inner {
        fun test(t: Test) {
            t.innerVal
            t.innerFun()
            t.Inner2()
            t.Inner2().inner2Fun()
        }
    }

    val innerVal = 1

    fun innerFun() {}

    inner class Inner2 {
        fun inner2Fun() {}
    }
}
