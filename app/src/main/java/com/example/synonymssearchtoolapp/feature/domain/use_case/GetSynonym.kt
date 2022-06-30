package com.example.synonymssearchtoolapp.feature.domain.use_case

import com.example.synonymssearchtoolapp.core.util.Resource
import com.example.synonymssearchtoolapp.feature.domain.model.Synonym
import com.example.synonymssearchtoolapp.feature.domain.repository.SynonymRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSynonym(
    private val repository: SynonymRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<Synonym>>> {
        if(word.isBlank()){
            return flow{ }
        }
        return repository.getSynonym(word)
    }
}