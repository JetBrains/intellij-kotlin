class Test {
    fun foo(f: (String) -> String): Test = this
}

fun test() {
    val abc = Test().foo()?.foo({ "str" }).foo { <caret> }
}

// WITHOUT_CUSTOM_LINE_INDENT_PROVIDER