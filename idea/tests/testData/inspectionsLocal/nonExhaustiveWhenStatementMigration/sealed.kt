// LANGUAGE_VERSION: 1.6

package sample

sealed class Base {
    class A : Base()
    class B : Base()
    class C : Base()
    class D : Base()
}

fun test(base: Base) {
    whe<caret>n (base) {
        is Base.A -> ""
    }
}