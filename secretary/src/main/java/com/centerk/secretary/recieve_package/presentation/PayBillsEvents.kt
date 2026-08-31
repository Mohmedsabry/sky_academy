package com.centerk.secretary.recieve_package.presentation

sealed interface PayBillsEvents {
    data class OnStudentClicked(
        val studentId: String
    ) : PayBillsEvents

    data class OnTyping(
        val query: String
    ) : PayBillsEvents

}

sealed interface PayBillsUiEvents : PayBillsEvents {
    data object NavigateUp : PayBillsUiEvents
    data class NavigateToDetails(val studentId: String) : PayBillsUiEvents
}