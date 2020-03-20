package com.dinesh.domain.repository

class EmployeeRepositoryImpl(
    private val remote: EmployeeRemote
) : EmployeeRepository {

    override suspend fun fetchEmployee() = remote.fetchEmployee()
}