package com.dinesh.domain.usecase


import com.dinesh.core.common.usecase.UseCase
import com.dinesh.domain.model.Employee
import com.dinesh.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow

class FetchEmployeeUseCase(
    private val repository: EmployeeRepository
) : UseCase<Flow<Employee>> {

    override suspend fun execute() = repository.fetchEmployee()
}