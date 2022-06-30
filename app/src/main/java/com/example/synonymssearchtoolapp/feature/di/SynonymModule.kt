package com.example.synonymssearchtoolapp.feature.di

import android.app.Application
import androidx.room.Room
import com.example.synonymssearchtoolapp.feature.data.local.Converter
import com.example.synonymssearchtoolapp.feature.data.local.SynonymDatabase
import com.example.synonymssearchtoolapp.feature.data.remote.SynonymApi
import com.example.synonymssearchtoolapp.feature.data.repository.SynonymRepositoryImpl
import com.example.synonymssearchtoolapp.feature.data.util.GsonParser
import com.example.synonymssearchtoolapp.feature.data.util.JsonParser
import com.example.synonymssearchtoolapp.feature.domain.repository.SynonymRepository
import com.example.synonymssearchtoolapp.feature.domain.use_case.GetSynonym
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SynonymModule {

    @Provides
    @Singleton
    fun provideGetSynonymUseCase(repository: SynonymRepository): GetSynonym {
        return GetSynonym(repository)
    }

    @Provides
    @Singleton
    fun provideSynonymRepository(
        db: SynonymDatabase,
        api: SynonymApi
    ): SynonymRepository {
        return SynonymRepositoryImpl(api,db.dao)
    }

    @Provides
    @Singleton
    fun provideSynonymDatabase(app: Application): SynonymDatabase {
        return Room.databaseBuilder(
            app,SynonymDatabase::class.java,"synonym_db"
        ).addTypeConverter(Converter(GsonParser(Gson())))
            .build()
    }
//addTypeConverter(Converter(GsonParser(Gson())))

    @Provides
    @Singleton
    fun provideSynonymApi(): SynonymApi {
       return Retrofit.Builder()
           .baseUrl(SynonymApi.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(SynonymApi::class.java)
    }
}