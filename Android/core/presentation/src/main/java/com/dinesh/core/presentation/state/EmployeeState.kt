package com.dinesh.core.presentation.state

import com.dinesh.core.presentation.model.PresentationEmployee
import com.svenjacobs.zen.core.state.State

data class EmployeeState(
    val employee: PresentationEmployee? = null,
    val sideEffectStates: SideEffectStates = SideEffectStates(),
    val position: Int = 0
) : State

data class SideEffectStates(
    val initialized: Boolean = true,
    val nextClicked: Boolean = false,
    val previousClicked: Boolean = false

)