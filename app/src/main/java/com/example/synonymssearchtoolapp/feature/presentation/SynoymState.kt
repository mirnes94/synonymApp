package com.example.synonymssearchtoolapp.feature.presentation

import com.example.synonymssearchtoolapp.feature.domain.model.Synonym

data class SynoymState(
    val synonymItems: List<Synonym> = emptyList(),
    val isLoading: Boolean = false
)
