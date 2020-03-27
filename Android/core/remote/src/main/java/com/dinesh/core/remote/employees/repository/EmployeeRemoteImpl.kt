package com.dinesh.core.remote.employees.repository

import com.dinesh.core.remote.employees.mapper.MoshiMapper
import com.dinesh.core.remote.employees.mapper.RemoteEmployeeMapper
import com.dinesh.core.remote.employees.model.RemoteData
import com.dinesh.core.remote.employees.model.RemoteEmployee
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
import kotlin.coroutines.CoroutineContext

class EmployeeRemoteImpl(
    private val operationsCoroutineContext: CoroutineContext,
    private val jsonMapper: MoshiMapper,
    private val mapper: RemoteEmployeeMapper
) : EmployeeRemote {

    companion object {
        private const val EMPLOYEES_URL = "http://dummy.restapiexample.com/api/v1/employee/2"
    }

    //todo: second stage
    @OptIn(KtorExperimentalAPI::class)
    fun initKtorClient() = HttpClient(Android) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }

    //todo : use context
    override suspend fun fetchDefaultEmployee() =
        mapper.mapTo(
            RemoteEmployee(
                status = "active",
                data = RemoteData(
                    id = "1",
                    employee_age = "31",
                    employee_name = "Robert",
                    employee_salary = "60,000",
                    profile_image = null
                )
            )
        )

    override suspend fun fetchEmployeeById(id: String) =
        mapper.mapTo(
            RemoteEmployee(
                status = "not active",
                data = RemoteData(
                    id = "1",
                    employee_age = "31",
                    employee_name = "Robert",
                    employee_salary = "60,000",
                    profile_image = null
                )
            )
        )

    //todo: second stage
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