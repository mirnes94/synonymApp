package com.example.synonymssearchtoolapp.feature.data.remote

import com.example.synonymssearchtoolapp.feature.data.remote.dto.SynonymDto
import retrofit2.http.GET
import retrofit2.http.Path

//https://api.dictionaryapi.dev/api/v2/entries/en/hello
//https://languagetools.p.rapidapi.com/synonyms/dog
//https://wordsapiv1.p.mashape.com/words/a/synonyms
interface SynonymApi {
    @GET("/words/{word}/synonyms")
    suspend fun getSynonym(
        @Path("word") word: String
    ): List<SynonymDto>

    companion object {
        const val BASE_URL = "https://wordsapiv1.p.mashape.com"
    }
}