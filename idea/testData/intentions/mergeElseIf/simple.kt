fun foo() {
    if (true) {

    } <caret>else {
        if (false) {
            foo()
        }
    }
}