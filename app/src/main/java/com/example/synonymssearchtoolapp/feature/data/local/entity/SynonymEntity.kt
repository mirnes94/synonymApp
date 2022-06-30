package com.example.synonymssearchtoolapp.feature.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.synonymssearchtoolapp.feature.data.local.Converter
import com.example.synonymssearchtoolapp.feature.domain.model.Synonym

@Entity (tableName = "synonymentity")
data class SynonymEntity(
    @ColumnInfo(name = "word")
    val word: String = "",
    @ColumnInfo(name = "synonyms")
    val synonyms: List<String> = emptyList(),
    @PrimaryKey val id: Int? = null

) {
    fun toSynonym(): Synonym {
        return Synonym(
            word = word,
            synonyms = synonyms
        )
    }
}
