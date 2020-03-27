package com.dinesh.domain.usecase

import com.dinesh.core.common.usecase.ArgUseCase
import com.dinesh.domain.model.Employee
import com.dinesh.domain.repository.EmployeeRepository

class FetchEmployeeByIdUseCase(
    private val repository: EmployeeRepository
) : ArgUseCase<String, Employee> {

    override suspend fun execute(argument: String) = repository.fetchEmployeeById(argument)


}