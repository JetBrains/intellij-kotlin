import java.io.File
import kotlin.io.extension as ext

fun foo(file: File): String {
    return file.<caret>
}

// ELEMENT: "ext"