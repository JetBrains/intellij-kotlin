// !WITH_NEW_INFERENCE
// !DIAGNOSTICS: -UNUSED_EXPRESSION -UNUSED_PARAMETER -UNUSED_VARIABLE -NOTHING_TO_INLINE
// !LANGUAGE: -InlineDefaultFunctionalParameters

inline fun inlineFun(lambda: () -> String) = lambda()

fun noInlineFun(lambda: () -> String) = lambda()


inline fun default0_1(lambda: () -> String, dlambda: () -> String = { lambda }) {
    lambda() + dlambda()
}

inline fun default0_2(lambda: () -> String, dlambda: () -> String = { noInlineFun (lambda) }) {
    lambda() + dlambda()
}



inline fun default1_0(lambda: () -> String, dlambda: () -> String = { lambda() }) {
    lambda() + dlambda()
}

inline fun default1_1(lambda: () -> String, noinline dlambda: () -> String = { lambda() }) {
    lambda() + dlambda()
}

inline fun default1_1crossinline(crossinline lambda: () -> String, noinline dlambda: () -> String = { lambda() }) {
    lambda() + dlambda()
}

inline fun default1_2(noinline lambda: () -> String, dlambda: () -> String = { lambda() }) {
    lambda() + dlambda()
}

inline fun default1_3(noinline lambda: () -> String, noinline dlambda: () -> String = { lambda() }) {
    lambda() + dlambda()
}




inline fun default2_0(lambda: () -> String, dlambda: () -> String = { inlineFun(lambda) }) {
    lambda() + dlambda()
}

inline fun default2_1(lambda: () -> String, noinline dlambda: () -> String = { inlineFun(lambda) }) {
    lambda() + dlambda()
}

inline fun default2_1crossinline(crossinline lambda: () -> String, noinline dlambda: () -> String = { inlineFun(lambda) }) {
    lambda() + dlambda()
}

inline fun default2_2(noinline lambda: () -> String, dlambda: () -> String = { inlineFun(lambda) }) {
    lambda() + dlambda()
}

inline fun default2_3(noinline lambda: () -> String, noinline dlambda: () -> String = { inlineFun(lambda) }) {
    lambda() + dlambda()
}
