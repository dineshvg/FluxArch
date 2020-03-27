package com.dinesh.domain.repository

import com.dinesh.domain.model.Employee

interface EmployeeRemote {

    suspend fun fetchDefaultEmployee(): Employee

    suspend fun fetchEmployeeById(id: String): Employee

}