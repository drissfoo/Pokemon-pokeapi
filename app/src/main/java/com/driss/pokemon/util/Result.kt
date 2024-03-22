package com.driss.pokemon.util

interface IResult<T> {
    val status: Status
    val data: T?

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    fun isEmpty() = this.data == null
            || (this.data is List<*>) && (this.data as? List<*>).isNullOrEmpty()
            || (this.data is Array<*>) && (this.data as? Array<*>).isNullOrEmpty()
            || (this.data is Map<*, *>) && (this.data as? Map<*, *>).isNullOrEmpty()

    fun isSuccess() = status == Status.SUCCESS
    fun isLoading() = status == Status.LOADING
    fun isError() = status == Status.ERROR
}


data class Result<T>(
    override val status: IResult.Status,
    override val data: T?,
    val message: String?,
    val errorCode: Int?
) : IResult<T> {

    companion object {
        fun <T> success(data: T? = null): Result<T> {
            return Result(IResult.Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, data: T? = null, code: Int? = null): Result<T> {
            return Result(IResult.Status.ERROR, data, message, code)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(IResult.Status.LOADING, data, null, null)
        }
    }
}