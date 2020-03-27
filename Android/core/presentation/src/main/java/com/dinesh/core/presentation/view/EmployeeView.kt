package com.dinesh.core.presentation.view

import com.dinesh.core.presentation.model.PresentationEmployee
import com.svenjacobs.zen.core.view.ZenView
import kotlinx.coroutines.flow.Flow

interface EmployeeView : ZenView {

    val onNextButtonClick: Flow<String>

    val onPreviousButtonClick: Flow<String>

    fun loadEmployeeCard(employee: PresentationEmployee)

    fun showError()

    fun showNextButton(visibility: Boolean)

    fun showPreviousButton(visibility: Boolean)

}