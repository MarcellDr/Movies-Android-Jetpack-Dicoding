package com.marcelldr.cinemaz.utils

import com.marcelldr.cinemaz.data.constants.GenreConstant
import org.json.JSONObject

object GenreUtils {
    fun getGenre(json: JSONObject, category: String): String {
        val array = json.getJSONArray("genre_ids")
        if (array.isNull(0)) {
            return ""
        }
        lateinit var genre: String
        if (category == "MOVIES") {
            genre = GenreConstant.MOVIES[array.getInt(0)] ?: ""
            for (i in 1 until array.length()) {
                if (GenreConstant.MOVIES.containsKey(array.getInt(i))) {
                    genre = StringBuilder(genre).append(" | ")
                        .append(GenreConstant.MOVIES[array.getInt(i)]).toString()
                }
            }
        } else {
            genre = GenreConstant.TV[array.getInt(0)] ?: ""
            for (i in 1 until array.length()) {
                if (GenreConstant.TV.containsKey(array.getInt(i))) {
                    genre = StringBuilder(genre).append(" | ")
                        .append(GenreConstant.TV[array.getInt(i)]).toString()
                }
            }
        }
        return genre
    }

    fun getGenre(map: Map<String, Any>, category: String): String {
        val list = map["genre"] as List<*>
        if (list.isEmpty()) {
            return ""
        }
        lateinit var genre: String
        if (category == "MOVIES") {
            genre = GenreConstant.MOVIES[list[0]] ?: ""
            for (i in 1 until list.size) {
                if (GenreConstant.MOVIES.containsKey(list[i])) {
                    genre = StringBuilder(genre).append(" | ")
                        .append(GenreConstant.MOVIES[list[i]]).toString()
                }
            }
        } else {
            genre = GenreConstant.TV[list[0]] ?: ""
            for (i in 1 until list.size) {
                if (GenreConstant.TV.containsKey(list[i])) {
                    genre = StringBuilder(genre).append(" | ")
                        .append(GenreConstant.TV[list[i]]).toString()
                }
            }
        }
        return genre
    }
}