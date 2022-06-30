package com.example.synonymssearchtoolapp.feature.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.synonymssearchtoolapp.feature.data.util.JsonParser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class Converter (
   private val jsonParser: JsonParser
        ) {
    @TypeConverter
    fun restoreList(listOfString: String?): List<String?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveListOfString(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }
}