package api.extension

import java.util.*

fun <T, R> Map<T, R>.random(): T {
    return this.keys.elementAt(Random(System.nanoTime()).nextInt(size))
}

fun <T, R> Map<T, R>.filterWithNonEmpty(func: (Map.Entry<T, R>) -> Boolean): Map<T, R> {
    val temp = this.filter { func.invoke(it) }
    if (temp.isEmpty()) return this
    return temp
}