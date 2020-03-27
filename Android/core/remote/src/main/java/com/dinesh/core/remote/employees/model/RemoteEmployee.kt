package com.dinesh.core.remote.employees.model

data class RemoteEmployee(
    val status: String,
    val data: RemoteData
)

data class RemoteData(
    val id: String,
    val employee_name: String,
    val employee_salary: String,
    val employee_age: String,
    val profile_image: String?
)