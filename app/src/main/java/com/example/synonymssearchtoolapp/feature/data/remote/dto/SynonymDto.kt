package com.example.synonymssearchtoolapp.feature.data.remote.dto

import com.example.synonymssearchtoolapp.feature.data.local.entity.SynonymEntity

data class SynonymDto(
    val word: String,
    val synonyms: List<String>
) {
    fun toSynonymEntity(): SynonymEntity{
        return SynonymEntity(
            word = word,
            synonyms =  synonyms
        )
    }
}