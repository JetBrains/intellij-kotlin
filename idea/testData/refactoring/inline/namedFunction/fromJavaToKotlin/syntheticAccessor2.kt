// ERROR: Inline of Java method is not supported for Kotlin

fun reproduceAccessorsBug() {
    val accessorsBug = AccessorsBug()
    accessorsBug.sm<caret>th = 1
    accessorsBug.setSmth(2)
}