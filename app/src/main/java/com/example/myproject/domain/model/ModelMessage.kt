package com.example.myproject.domain.model

data class ModelMessage(
    var messageType : String,
    var messageData : String,
    var messageSender : String,
    var messageReceiver : String
)