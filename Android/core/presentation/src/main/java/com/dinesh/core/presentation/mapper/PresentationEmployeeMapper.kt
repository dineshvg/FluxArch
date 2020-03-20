package com.dinesh.core.presentation.mapper

import com.dinesh.core.common.mapper.TwoWayMapper
import com.dinesh.core.presentation.model.PresentationEmployee
import com.dinesh.domain.model.Employee

class PresentationEmployeeMapper : TwoWayMapper<Employee, PresentationEmployee> {

    override fun mapFrom(to: PresentationEmployee) =
        Employee(
            id = to.id,
            name = to.name,
            salary = to.salary,
            age = to.age
        )

    override fun mapTo(from: Employee) =
        PresentationEmployee(
            id = from.id,
            name = from.name,
            salary = from.salary,
            age = from.age
        )
}