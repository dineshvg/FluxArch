package com.dinesh.domain.repository

import com.dinesh.domain.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {

    suspend fun fetchEmployee(): Flow<Employee>
}