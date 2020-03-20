package com.dinesh.core.remote.employees.repository

import com.dinesh.core.remote.employees.mapper.MoshiMapper
import com.dinesh.core.remote.employees.mapper.RemoteEmployeeMapper
import com.dinesh.domain.repository.EmployeeRemote
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.logging.ANDROID
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class EmployeeRemoteImpl(
    private val operationsCoroutineContext: CoroutineContext,
    private val jsonMapper: MoshiMapper,
    private val mapper: RemoteEmployeeMapper
) : EmployeeRemote {

    companion object {
        private const val EMPLOYEES_URL = "http://dummy.restapiexample.com/api/v1/employee/2"
    }

    //todo: add to DI
    @OptIn(KtorExperimentalAPI::class)
    fun initKtorClient() = HttpClient(Android) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }

    override suspend fun fetchEmployee() =
        withContext(operationsCoroutineContext) {
            return@withContext HttpClient()
                .downloadEmployees()
                .mapNotNull {
                    mapper.mapTo(it)
                }
        }

    private suspend fun HttpClient.downloadEmployees() =
        flow {
            val response: HttpResponse = this@downloadEmployees.request<HttpResponse> {
                url(EMPLOYEES_URL)
                method = HttpMethod.Get
            }
            if (response.status.isSuccess()) {
                jsonMapper.jsonAdapter.fromJson(response.readText())?.let {
                    emit(it)
                } ?: throw Exception("JSON not found")
            } else {
                throw Exception("JSON not found")
            }
        }
}