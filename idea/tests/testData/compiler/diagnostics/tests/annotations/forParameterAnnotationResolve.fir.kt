annotation class Ann(val x: Int)

data class A(val x: Int, val y: Int)

fun bar(): Array<A> = null!!

fun foo() {
    for (@Ann(1) i in 1..100) {}
    for (@Ann(2) i in 1..100) {}

    for (@Ann(3) (x, @Ann(4) y) in bar()) {}

    for (@Err() (x,y) in bar()) {}
}
