package com.dinesh.feature.employees.view

import androidx.fragment.app.Fragment
import com.dinesh.core.presentation.model.PresentationEmployee
import com.dinesh.core.presentation.view.EmployeeView
import kotlinx.coroutines.flow.Flow

class EmployeeFragment : Fragment(), EmployeeView {

    override val onNextButtonClick: Flow<String>
        get() = TODO("Not yet implemented")
    override val onPreviousButtonClick: Flow<String>
        get() = TODO("Not yet implemented")

    override fun loadEmployeeCard(employee: PresentationEmployee) {
        TODO("Not yet implemented")
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun showNextButton(visibility: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showPreviousButton(visibility: Boolean) {
        TODO("Not yet implemented")
    }


}