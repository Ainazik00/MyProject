package com.example.myproject.presentation.HomePageLayout.Chat


import com.example.myproject.presentation.IViewsHandling

interface IChatView : IViewsHandling {

    fun openMessageFragment(chatId: String) {}
}