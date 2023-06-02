package com.diseno.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

suspend fun postData(jsonBody: String): String = withContext(Dispatchers.IO) {
    val client = OkHttpClient()

    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val requestBody = jsonBody.toRequestBody(mediaType)

    val request = Request.Builder()
        .url("https://backend-app.revis8466.repl.co/user")
            .post(requestBody)
            .build()

            val response = client.newCall(request).execute()

    return@withContext response.body?.string() ?: ""
}
suspend fun fetchDataFromApi(email: String): String = withContext(Dispatchers.IO) {
    val client = OkHttpClient()

    val url = "https://backend-app.revis8466.repl.co/email".toHttpUrlOrNull()?.newBuilder()
        ?.addQueryParameter("json", "{\"email\":\"${email}\"}")
    ?.build()

    val request = url?.let {
        Request.Builder()
            .url(it)
            .get()
            .build()
    }

    val response = request?.let { client.newCall(it).execute() }

    return@withContext response?.body?.string() ?: ""
}
