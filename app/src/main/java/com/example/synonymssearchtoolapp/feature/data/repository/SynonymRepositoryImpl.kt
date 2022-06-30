package com.example.synonymssearchtoolapp.feature.data.repository

import com.example.synonymssearchtoolapp.core.util.Resource
import com.example.synonymssearchtoolapp.feature.data.local.SynonymDao
import com.example.synonymssearchtoolapp.feature.data.remote.SynonymApi
import com.example.synonymssearchtoolapp.feature.domain.model.Synonym
import com.example.synonymssearchtoolapp.feature.domain.repository.SynonymRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SynonymRepositoryImpl(
    private val api: SynonymApi,
    private val dao: SynonymDao
): SynonymRepository {
    override fun getSynonym(word: String): Flow<Resource<List<Synonym>>> = flow {
        emit(Resource.Loading())

        val synonyms = dao.getSynonym(word).map { it.toSynonym() }
        emit(Resource.Loading(data = synonyms))

        try {
            val remoteSynonym = api.getSynonym(word)
            dao.deleteSynonym(remoteSynonym.map { it.word })
            dao.insertSynonym(remoteSynonym.map { it.toSynonymEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = synonyms
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = synonyms
            ))
        }

        val newSynonym = dao.getSynonym(word).map { it.toSynonym() }
        emit(Resource.Success(newSynonym))
    }
}