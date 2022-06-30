package com.example.synonymssearchtoolapp.feature.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.synonymssearchtoolapp.feature.data.local.entity.SynonymEntity

@Dao
interface SynonymDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSynonym(synonyms: List<SynonymEntity>)

    @Query("DELETE FROM synonymentity WHERE word IN (:words)")
    suspend fun deleteSynonym(words: List<String>)

    @Query("SELECT * FROM synonymentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getSynonym(word: String): List<SynonymEntity>

}