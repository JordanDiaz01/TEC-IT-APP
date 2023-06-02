package com.diseno.login.ui.viewModel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.diseno.login.Publication
import com.diseno.login.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.RequestBody
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class mainViewModel :ViewModel() {
    private val _currentPage = mutableStateOf("login")
    val currentPage: State<String> = _currentPage

    private val _hideBottomNav = mutableStateOf(false)
    val hideBottomNav: State<Boolean> = _hideBottomNav

    private val _currentState = mutableStateOf("")
    val currentState: State<String> = _currentState

    private val _allUsers = mutableStateOf(emptyList<Usuario>())
    val allUsers: State<List<Usuario>> = _allUsers

    private val _allPubli = mutableStateOf(emptyList<Publication>())
    val allPubli: State<List<Publication>> = _allPubli

    private val _currentUser = mutableStateOf(Usuario("", "", null, "", 0, "", "",isVendedor = false))
    val currentUser: State<Usuario> = _currentUser


    private val client = OkHttpClient()

    fun setCurrentPage(value: String) {
        _currentPage.value = value
    }
    fun setAllUser(value: List<Usuario>) {
        _allUsers.value = value
    }
    fun setAllPubli(value: List<Publication>) {
        _allPubli.value = value
    }
    fun setCurrentUser(value: Usuario) {
        _currentUser.value = value
    }
    fun setHideBottomNav(value: Boolean) {
        _hideBottomNav.value = value
    }
    fun setCurrentState(value: String) {
        _currentState.value = value
    }

    fun userPut(url: String, json: String) {
        val body = json.toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .put(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejar el error de la solicitud
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                println(responseData)
                viewModelScope.launch(Dispatchers.Main) {
                    if (responseData != null) {

                    }
                }
            }
        })
    }

    fun publiPost(url: String, json: String) {
        val body = json.toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .put(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejar el error de la solicitud
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                println(responseData)
                viewModelScope.launch(Dispatchers.Main) {
                    if (responseData != null) {

                    }
                }
            }
        })
    }

    fun getUser(email: String,password:String): Boolean{
        allUsers.value.forEach { user ->
            println(user)
            if (user.email == email && user.user_pass == password){
                setCurrentUser(user)
                return true
            }
        }
        return false
    }

    fun getAllPubli(url: String) {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejar el error de la solicitud
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                println(responseData)
                viewModelScope.launch(Dispatchers.Main) {
                    if (responseData != null) {
                        val listType = object : TypeToken<List<Publication>>() {}.type
                        setAllPubli(Gson().fromJson<List<Publication>>(responseData, listType))
                    }
                }
            }
        })
    }


    fun getAllUsers(url: String) {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejar el error de la solicitud
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                println(responseData)
                viewModelScope.launch(Dispatchers.Main) {
                    if (responseData != null) {
                        val listType = object : TypeToken<List<Usuario>>() {}.type
                        setAllUser(Gson().fromJson<List<Usuario>>(responseData, listType))
                        println(allUsers)
                    }
                }
            }
        })
    }

}