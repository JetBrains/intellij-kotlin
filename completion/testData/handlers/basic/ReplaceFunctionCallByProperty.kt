class C {
    val bar: Int
}

fun foo(c: C) {
    val v = c.<caret>getBar()
}

// ELEMENT: bar
// CHAR: '\t'
