// !WITH_NEW_INFERENCE
// !CHECK_TYPE

fun <T> magic(): T = null!!

class Q {
    private fun <E> foo() = {
        class C {
            val prop: E = magic()
        }
        C()
    }

    private var x = foo<CharSequence>()()
    private var y = foo<String>()()

    fun bar() {
        x = y
        x = foo<CharSequence>()()
        y = foo<String>()()

        x.prop.checkType { <!INAPPLICABLE_CANDIDATE!>_<!><CharSequence>() }
        y.prop.checkType { <!INAPPLICABLE_CANDIDATE!>_<!><String>() }
    }
}
