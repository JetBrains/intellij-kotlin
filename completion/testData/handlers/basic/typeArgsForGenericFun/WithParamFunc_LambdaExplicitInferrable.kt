fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapTo(destination: C, transform: (T) -> R): C = TODO()

// T - from receiver type Iterable<T> => (T)
// C - from 'destination' => (T, C)
// (T) -> R - T is in (T, C), R is inferrable afterwards => (T, C, R)

fun main() {
    val strings = listOf("abc", "def")
    strings.mapTo(mutableSetOf(), {it.length}).ma<caret>
}
// ELEMENT: map