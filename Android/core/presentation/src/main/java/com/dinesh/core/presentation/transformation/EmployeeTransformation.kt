package com.dinesh.core.presentation.transformation

import com.dinesh.core.presentation.mapper.PresentationEmployeeMapper
import com.dinesh.core.presentation.state.EmployeeState
import com.dinesh.domain.action.EmployeeAction
import com.dinesh.domain.usecase.FetchEmployeeUseCase
import com.svenjacobs.zen.core.state.DefaultStateAccessor
import com.svenjacobs.zen.core.state.StateAccessor
import com.svenjacobs.zen.core.state.Transformer
import com.svenjacobs.zen.core.state.withDefault
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class EmployeeTransformation(
    private val fetchEmployeeUseCase: FetchEmployeeUseCase,
    private val mapper: PresentationEmployeeMapper
) : Transformer.Transformation<EmployeeAction, EmployeeState> {

    override suspend fun invoke(
        action: EmployeeAction,
        state: StateAccessor<EmployeeState>
    ): Flow<EmployeeState> {
        val defaultState = state.withDefault { EmployeeState() }
        return flowOfState(
            when (action) {
                is EmployeeAction.LoadAction -> loadAction(defaultState)
                is EmployeeAction.ChangeNextAction -> {
                    //todo : show next employee
                }
                is EmployeeAction.ChangePreviousAction -> {
                    //todo: show previous employee
                }
            }
        )
    }

    private suspend fun loadAction(
        state: DefaultStateAccessor<EmployeeState>
    ): EmployeeState {
        val presentationEmployee = mapper.mapTo(
            fetchEmployeeUseCase.execute()
        )
        return state.value.copy(
            employee = presentationEmployee
        )
    }


    @Suppress("UNCHECKED_CAST")
    fun <T> flowOfState(value: Any?, nullToEmpty: Boolean = true): Flow<T> =
        if (value is Flow<*>)
            value as Flow<T>
        else if (value == null && nullToEmpty)
            emptyFlow()
        else
            flowOf(value as T)
}