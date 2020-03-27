package com.dinesh.core.presentation.contract

import com.dinesh.core.presentation.state.EmployeeState
import com.dinesh.core.presentation.view.EmployeeView
import com.dinesh.domain.action.EmployeeAction
import com.svenjacobs.zen.core.action.ActionPublisher
import com.svenjacobs.zen.core.master.ZenMaster
import com.svenjacobs.zen.core.state.sideEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class EmployeeZenMasterContract : ZenMaster.Contract<EmployeeView, EmployeeAction, EmployeeState> {

    override fun CoroutineScope.onViewReady(
        view: EmployeeView,
        publisher: ActionPublisher<EmployeeAction>
    ) {
        publisher.publish(EmployeeAction.LoadAction)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun actions(view: EmployeeView) =
        merge(
            view.onNextButtonClick.map { EmployeeAction.ChangeNextAction(it) },
            view.onPreviousButtonClick.map { EmployeeAction.ChangePreviousAction(it) }
        )


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun stateChanges(state: Flow<EmployeeState>, view: EmployeeView) =
        merge(
            state.sideEffect(
                select = { sideEffectStates.initialized },
                onEach = {
                    loadEmployeeInView(state, view)
                    view.showPreviousButton(false)
                    view.showNextButton(true)
                }
            ),
            state.sideEffect(
                select = { sideEffectStates.nextClicked },
                onEach = {
                    loadEmployeeInView(state, view)
                    view.showPreviousButton(true)
                    view.showNextButton(true)
                }
            ),
            state.sideEffect(
                select = { sideEffectStates.previousClicked },
                onEach = {
                    loadEmployeeInView(state, view)
                    view.showPreviousButton(true)
                    view.showNextButton(true)
                }
            )
        )

    private suspend fun loadEmployeeInView(stateFlow: Flow<EmployeeState>, view: EmployeeView) {
        stateFlow.collect { employeeState ->
            employeeState.employee?.let {
                view.loadEmployeeCard(it)
            } ?: view.showError()
        }
    }

}