// "Add '@UnsafeVariance' annotation" "true"
interface Foo<in E> {
    fun bar(): E<caret>
}