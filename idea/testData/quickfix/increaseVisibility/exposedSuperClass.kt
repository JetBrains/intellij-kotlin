// "Make 'Data' protected" "true"
// ACTION: Make 'First' private
// ACTION: Make 'Data' public

class Outer {
    private open class Data(val x: Int)

    protected class First : <caret>Data(42)
}