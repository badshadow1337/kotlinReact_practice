package tools

inline operator fun <reified T> Array<T>.plus(element: T) =
    Array(size + 1) {
        if (it == size)
            element
        else
            get(it)
    }

inline operator fun <reified T> Array<T>.minus(element: T) =
    (this.filter { it!=element }).toTypedArray()

inline fun <reified T> Array<T>.replace(
    filter: (Int, T) -> Boolean,
    transform: (T) -> T
) = Array(size) { index ->
    val element = get(index)
    if (filter(index, element))
        transform(element)
    else
        element
}

