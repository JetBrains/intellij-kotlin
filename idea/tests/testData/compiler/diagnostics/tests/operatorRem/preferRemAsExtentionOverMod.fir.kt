// !LANGUAGE: -ProhibitOperatorMod
// !DIAGNOSTICS: -UNUSED_PARAMETER

class Foo {
}
operator fun Foo.mod(x: Int): Foo = Foo()
operator fun Foo.rem(x: Int): Int = 0

fun foo() {
    takeInt(Foo() % 1)
}

fun takeInt(x: Int) {}