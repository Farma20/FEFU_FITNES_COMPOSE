package com.example.fefu_fitnes_compose.Domain.use_case

data class ValidationResult(
    var successful:Boolean,
    var errorMessage: String? = null
)
