public interface Base {
    fun test() = "Base"
}

class Delegate : Base {
    override fun test() = "Base"
}

public open class MyClass : Base by Delegate()

fun box(): String {
    <!MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>object<!> : MyClass(), Base by Delegate() {
    }
    return "OK"
}

