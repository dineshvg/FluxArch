package com.dinesh.domain.action

import com.svenjacobs.zen.core.action.Action

sealed class EmployeeAction : Action {

    object LoadAction : EmployeeAction()

    class ChangeNextAction(currentId: String) : EmployeeAction()

    class ChangePreviousAction(currentId: String) : EmployeeAction()
}