// "Wrap with '?.let { ... }' call" "true"
// ERROR: Expression 'it' of type 'Unit' cannot be invoked as a function. The function 'invoke()' is not found
// ERROR: Function invocation 'foo2()' expected
// ERROR: Reference has a nullable type '(Str.() -> Unit)?', use explicit '?.invoke()' to make a function-like call instead
// WITH_RUNTIME

interface Str {
    val foo: (() -> Unit)?
}

object Str2 {
    val foo2: (Str.() -> Unit)? = null

    fun bar(s: Str) {
        s.<caret>foo2()
    }
}

