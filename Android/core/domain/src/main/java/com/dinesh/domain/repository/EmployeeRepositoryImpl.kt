package com.dinesh.domain.repository

class EmployeeRepositoryImpl(
    private val remote: EmployeeRemote
) : EmployeeRepository {

    override suspend fun fetchDefaultEmployee() = remote.fetchDefaultEmployee()

    override suspend fun fetchEmployeeById(id: String) = remote.fetchEmployeeById(id)
}