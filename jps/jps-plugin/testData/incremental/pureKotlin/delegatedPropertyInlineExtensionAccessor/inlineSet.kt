package inline

import kotlin.reflect.KProperty

inline operator fun Inline.setValue(receiver: Any?, prop: KProperty<*>, value: Int) {
    println(value)
}
