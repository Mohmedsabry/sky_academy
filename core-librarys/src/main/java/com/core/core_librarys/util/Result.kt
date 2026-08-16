package com.core.core_librarys.util

sealed interface Result<D, E : Error> {
    data class Success<D, E : Error>(val data: D) : Result<D, E>
    data class Failure<D, E : Error>(val error: E, val code: Int = 0) : Result<D, E>
}

inline fun <D, E : Error> Result<D, E>.onSuccess(onSuccess: (data: D) -> Unit): Result<D, E> {
    return when (this) {
        is Result.Failure -> this
        is Result.Success -> {
            onSuccess(this.data)
            this
        }
    }
}

inline fun <D, E : Error> Result<D, E>.onFailure(
    onFailure: (error: E, code: Int) -> Unit
): Result<D, E> {
    return when (this) {
        is Result.Failure -> {
            onFailure(error, code)
            this
        }

        is Result.Success -> {
            this
        }
    }
}