package com.dinesh.core.remote.employees.model

data class RemoteEmployee(
    val status: String,
    val data: Data
)

data class Data(
    val id: String,
    val employee_name: String,
    val employee_salary: String,
    val employee_age: String,
    val profile_image: String?
)

/**
 * Example of how the JSON object looks like:
{
"status": "success",
"data": {
"id": "2",
"employee_name": "Garrett Winters",
"employee_salary": "170750",
"employee_age": "63",
"profile_image": ""
}
}
 **/