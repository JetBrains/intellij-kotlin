// "Opt-in for 'A::class' on 'root'" "true"
// WITH_RUNTIME
@RequiresOptIn
annotation class A

@A
fun f1() {}

@OptIn()
fun root() {
    <caret>f1()
}