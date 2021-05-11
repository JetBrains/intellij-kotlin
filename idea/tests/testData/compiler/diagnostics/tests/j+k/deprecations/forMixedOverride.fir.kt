// FILE: J.java
public class J {
    @Deprecated
    public void foo() {}
}
// FILE: J2.java
public class J2 extends J implements WithDeprecation {
    @Override
    public void foo() {}
}

// FILE: main.kt
interface WithDeprecation {
    @Deprecated("")
    fun foo()
}

class A : J(), WithDeprecation {
    override fun foo() {}
}

fun main() {
    J().foo()

    J2().foo()
    A().foo()
}
