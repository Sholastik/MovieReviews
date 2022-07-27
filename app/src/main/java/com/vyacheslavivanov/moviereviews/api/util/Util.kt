package com.vyacheslavivanov.moviereviews.api.util

import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

inline fun <T> Response<T>.fold(
    toBody: Response<T>.() -> T? = {
        body()
    },
    toException: Response<T>.() -> Exception = {
        HttpException(this)
    }
): Result<T> {
    return runCatching {
        if (isSuccessful) {
            toBody() ?: throw UnknownError(toString())
        } else {
            throw toException()
        }
    }
}

inline fun <T, R> Result<T>.foldLogging(onSuccess: T.() -> R): Result<R> {
    return runCatching {
        onSuccess(getOrThrow())
    }.onFailure {
        Timber.e(it)
    }
}
