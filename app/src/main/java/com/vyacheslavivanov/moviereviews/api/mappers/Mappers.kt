package com.vyacheslavivanov.moviereviews.api.mappers

inline fun <I, O> mapList(input: List<I>, mapSingle: (I) -> O): List<O> {
    return input.map { mapSingle(it) }
}
