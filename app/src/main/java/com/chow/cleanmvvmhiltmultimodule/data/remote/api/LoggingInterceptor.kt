package com.chow.cleanmvvmhiltmultimodule.data.remote.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import java.io.IOException
import java.net.SocketTimeoutException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * Custom interceptor responsible for printing curl logs
 */
class LoggingInterceptor : Interceptor {
    private val logger = HttpLoggingInterceptor.Logger.DEFAULT

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        //request of api
        val requestBodyBuffer = Buffer()
        request.body?.writeTo(requestBodyBuffer)
        val requestBody = requestBodyBuffer.clone().readUtf8()

        //startNs: time at start to send request api
        val startNs = System.nanoTime()

        //response: response api
        val response: Response
        //try request api
        try {
            response = chain.proceed(request)
        } catch (e: SocketTimeoutException) {
            //log curl when api timeout
            logger.log("╭--- TIMEOUT ${request.url}")
            logger.log("| Request: $requestBody")
            logger.log("╰---> HTTP FAILED: $e")
            throw Throwable()
        } catch (e: Exception) {
            throw e
        }
        //tookMs: time to receive response from api since api request
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        val responseBody = response.body
        val source = responseBody?.source()
        source?.request(Long.MAX_VALUE) // Buffer the entire body.
        val contentType = responseBody?.contentType()

        val buffer = source?.buffer
        var charset = Charset.forName("UTF-8")
        if (contentType != null) charset = contentType.charset(charset)

        val responseBodyString = if (responseBody?.contentLength() != 0L) {
            buffer?.clone()?.readString(charset) ?: ""
        } else ""

        //log curl when api success
        logger.log(
            "╭--- ${response.code}${
                if (response.message.isEmpty()) "" else ' ' + response.message
            } ${response.request.url}"
        )
        if (requestBody.isNotBlank()) {
            logger.log("| Request: $requestBody")
        }
        if (responseBodyString.isNotBlank()) {
            logger.log("| Response: $responseBodyString")
        }
        logger.log("╰---> (${tookMs}ms) END HTTP (${buffer?.size}-byte body)")
        return response
    }
}