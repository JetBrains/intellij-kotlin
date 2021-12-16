// "Change parameter 'x' type of function 'bar' to 'List<T & Any>'" "false"
// ERROR: Type mismatch: inferred type is List<T> but List<T & Any> was expected
// ACTION: Add 'x =' to argument
// ACTION: Change parameter 'x' type of function 'foo' to 'List<T>'
// ACTION: Create function 'foo'
// LANGUAGE_VERSION: 1.7
package a

fun <T> foo(x: List<T & Any>) {}

fun <T> bar(x: List<T>) {
    foo(x<caret>)
}
