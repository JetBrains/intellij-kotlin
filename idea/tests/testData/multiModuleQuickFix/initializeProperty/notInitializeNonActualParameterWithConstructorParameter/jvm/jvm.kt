// "Initialize with constructor parameter" "false"
// ACTION: Add getter
// ACTION: Add initializer
// ACTION: Convert member to extension
// ACTION: Convert property to function
// ACTION: Introduce backing property
// ACTION: Make 'b' 'abstract'
// ACTION: Move to companion object
// ACTION: Move to constructor
// ERROR: Property must be initialized or be abstract

actual class SimpleWConstructor {
    val <caret>b: String
    constructor(s: String)
    actual constructor(i: Int)
}