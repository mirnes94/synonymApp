package com.example.synonymssearchtoolapp.feature.domain.repository

import com.example.synonymssearchtoolapp.core.util.Resource
import com.example.synonymssearchtoolapp.feature.domain.model.Synonym
import kotlinx.coroutines.flow.Flow

interface SynonymRepository {

    fun getSynonym(word: String): Flow<Resource<List<Synonym>>>
}