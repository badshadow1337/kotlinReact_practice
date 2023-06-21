package data

enum class Mode {
    Short,
    Full;

    operator fun not() =
        if (this == Full)
            Short
        else
            Full
}

