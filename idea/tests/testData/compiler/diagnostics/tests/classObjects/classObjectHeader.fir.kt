package test

open class ToResolve<SomeClass>(f : (Int) -> Int)
fun testFun(a : Int) = 12

class TestSome<P> {
    companion object : ToResolve<P>({testFun(it)}) {
    }
}