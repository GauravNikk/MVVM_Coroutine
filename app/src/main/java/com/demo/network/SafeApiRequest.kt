package com.demo.network

import android.util.Log
import com.demo.network.exceptions.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    private val maxRetryAttempts = 3
    private val initialRetryDelayMillis = 2000L // Initial delay of 1 second

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        var currentAttempt = 0
        var retryDelay = initialRetryDelayMillis

        while (true) {
            try {
                val response = call.invoke()
                Log.d("ApiRequest", "Response: ${response.raw()}")

                return if (response.isSuccessful) {
                    response.body() ?: throw ApiException("Response body is null")
                } else {
                    handleErrorResponse(response)
                }
            } catch (e: Exception) {
                Log.e("ApiRequest", "Exception on attempt $currentAttempt: ${e.message}", e)
                currentAttempt++
                if (currentAttempt >= maxRetryAttempts) {
                    throw ApiException(" ${e.message} Or Network or conversion error after $maxRetryAttempts attempts: ")
                }

                // Implement exponential backoff
                delayWithExponentialBackoff(retryDelay)
                retryDelay *= 2 // Double the delay for the next retry
            }
        }
    }

    private fun <T : Any> handleErrorResponse(response: Response<T>): T {
        val error = response.errorBody()?.string()
        val message = StringBuilder()
        error?.let {
            try {
                val jsonError = JSONObject(it)
                message.append(jsonError.optString("message", "Unknown error"))
            } catch (e: JSONException) {
                message.append("Error parsing error message")
            }
        }
        message.append(" Error Code: ${response.code()}")
        throw ApiException(message.toString())
    }

    private suspend fun delayWithExponentialBackoff(delayMillis: Long) {
        kotlinx.coroutines.delay(delayMillis)
    }
}








