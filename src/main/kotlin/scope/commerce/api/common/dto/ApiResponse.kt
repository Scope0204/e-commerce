package scope.commerce.api.common.dto

import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    val code: Int,
    val success: Boolean,
    val message: String,
    val httpStatus: Int,
    val httpStatusMessage: String,
    val errorMessage: String,
    val data: DataWrapper<T>? = null
) {
    data class DataWrapper<T>(
        val content: T,
        val pagination: Pagination? = null
    )

    data class Pagination(
        val page: Int,
        val size: Int,
        val totalElements: Int,
        val totalPages: Double
    )

    companion object {
        // 성공 응답 생성 - 단건 조회
        fun <T> success(
            content: T,
            message: String = "OK"
        ): ApiResponse<T> =
            ApiResponse(
                code = HttpStatus.OK.value(),
                success = true,
                message = message,
                httpStatus = HttpStatus.OK.value(),
                httpStatusMessage = HttpStatus.OK.reasonPhrase,
                errorMessage = "",
                data = DataWrapper(content = content)
            )

        // 성공 응답 생성 - 리스트 조회
        fun <T> success(
            content: T,
            pagination: Pagination?,
            message: String = "OK"
        ): ApiResponse<T> =
            ApiResponse(
                code = HttpStatus.OK.value(),
                success = true,
                message = message,
                httpStatus = HttpStatus.OK.value(),
                httpStatusMessage = HttpStatus.OK.reasonPhrase,
                errorMessage = "",
                data = DataWrapper(content = content, pagination = pagination)
            )

        // 실패 응답 생성
        fun <T> error(
            httpStatus: HttpStatus,
            message: String,
            errorMessage: String = ""
        ): ApiResponse<T> =
            ApiResponse(
                code = httpStatus.value(),
                success = false,
                message = message,
                httpStatus = httpStatus.value(),
                httpStatusMessage = httpStatus.reasonPhrase,
                errorMessage = errorMessage,
                data = null
            )
    }
}
