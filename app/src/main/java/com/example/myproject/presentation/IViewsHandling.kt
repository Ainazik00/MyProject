package com.example.myproject.presentation

interface IViewsHandling {

    fun hideProgressBar() {}
    fun showProgressBar() {}
    fun showError(error : String) {}
    fun changeViewsVisibility() {}
    fun showHomePage() {}
    fun dismissOtpBottomSheetDialogFragment() {}
    fun getUserId() : String
}