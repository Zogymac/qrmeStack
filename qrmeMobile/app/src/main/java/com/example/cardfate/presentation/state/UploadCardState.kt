package com.example.cardfate.presentation.state

sealed class UploadCardState()

object UploadProgress : UploadCardState()
object UploadError : UploadCardState()
object UploadSuccess : UploadCardState()
