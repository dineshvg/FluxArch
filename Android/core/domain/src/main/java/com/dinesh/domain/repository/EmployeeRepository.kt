package com.dinesh.domain.repository

import com.dinesh.domain.model.Employee

interface EmployeeRepository {

    suspend fun fetchDefaultEmployee(): Employee

    suspend fun fetchEmployeeById(id: String): Employee
}