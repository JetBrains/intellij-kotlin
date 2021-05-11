// FILE: KotlinFile.kt
abstract class KotlinClass : JavaInterface1, JavaInterface2 {
    override fun getSomething(): String = ""
}

fun foo(k: KotlinClass) {
    useString(k.getSomething())
    useString(k.something)
    if (k.something == null) return

    k.setSomething(1)
    k.something = 1
}

fun useString(i: String) {}

// FILE: JavaInterface1.java
public interface JavaInterface1 {
    String getSomething();
}

// FILE: JavaInterface2.java
public interface JavaInterface2 {
    void setSomething(int value);
}
