package com.dinesh.core.remote.employees.mapper

import com.dinesh.core.common.mapper.Mapper
import com.dinesh.core.remote.employees.model.RemoteEmployee
import com.dinesh.domain.model.Employee

class RemoteEmployeeMapper : Mapper<RemoteEmployee, Employee> {

    override fun mapTo(from: RemoteEmployee) =
        Employee(
            id = from.data.id,
            name = from.data.employee_name,
            salary = from.data.employee_salary,
            age = from.data.employee_age
        )
}