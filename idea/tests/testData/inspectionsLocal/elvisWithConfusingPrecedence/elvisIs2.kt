// FIX: Add parentheses to elvis right-hand side
object KtFile
class Stub {
    fun isTopLevel() = true
}

val parent = KtFile
val stub: Stub? = null

fun main() {
    val x = stub?.isTopLevel() <caret>?: parent is KtFile
}