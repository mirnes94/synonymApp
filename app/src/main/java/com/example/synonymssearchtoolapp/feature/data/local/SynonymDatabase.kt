package com.example.synonymssearchtoolapp.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.synonymssearchtoolapp.feature.data.local.entity.SynonymEntity

@Database(
    entities = [SynonymEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class SynonymDatabase: RoomDatabase() {

    abstract val dao: SynonymDao

}