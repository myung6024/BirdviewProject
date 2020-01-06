package com.runeanim.birdviewproject.util

import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

class ErrorInterceptor @Inject constructor(
    private val jsonParser: JsonParser,
    private val charsetUTF8: Charset
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)

        return if (response.code >= 200) {
            throwError(response)
            response
        } else {
            response
        }
    }

    @Throws(IOException::class)
    private fun throwError(response: Response) {
        response.body?.let {
            val source = it.source()
            source.request(java.lang.Long.MAX_VALUE)
            val buffer = source.buffer

            it.contentType()?.let { contentType ->
                val charset = contentType.charset(charsetUTF8)
                if (charset != null && it.contentLength() != 0L) {
                    val responseJSON = buffer.clone().readString(charset)
                    val statusCode: Int?
                    try {
                        val objects = jsonParser.parse(responseJSON).asJsonObject
                        statusCode = objects.getAsJsonPrimitive("statusCode").asInt
                        if (statusCode < 400) {
                            return
                        }
                        throw Throwable(statusCode.toString())
                    } catch (error: JsonParseException) {
                        throw Throwable("")
                    }
                } else {
                    throw Throwable("")
                }
            } ?: throw Throwable("")
        } ?: throw Throwable("")
    }
}
