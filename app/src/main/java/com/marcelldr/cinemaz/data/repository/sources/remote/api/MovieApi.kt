package com.marcelldr.cinemaz.data.repository.sources.remote.api

import android.content.Context
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.marcelldr.cinemaz.BuildConfig
import com.marcelldr.cinemaz.data.repository.sources.remote.response.DataResponse
import com.marcelldr.cinemaz.utils.GenreUtils
import org.json.JSONObject

class MovieApi private constructor(context: Context) {
    companion object {
        private const val API_KEY = BuildConfig.MOVIEDB_KEY
        private const val BASE_URL_MOVIES = "https://api.themoviedb.org/3/movie/popular"
        private const val BASE_URL_TV_SERIES = "https://api.themoviedb.org/3/tv/popular"

        @Volatile
        private var INSTANCE: MovieApi? = null
        fun getInstance(context: Context): MovieApi = INSTANCE ?: synchronized(this) {
            INSTANCE ?: MovieApi(context).apply { INSTANCE = this }
        }
    }

    init {
        AndroidNetworking.initialize(context)
    }

    interface MovieAPICallback {
        fun onResponse(response: List<DataResponse>)
    }

    fun getMovies(movieAPICallback: MovieAPICallback) {
        AndroidNetworking.get(BASE_URL_MOVIES)
            .addQueryParameter("api_key", API_KEY)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val list = jsonParse(response!!, "MOVIES")
                    movieAPICallback.onResponse(list)
                    Log.i("MovieSuccess", "MovieSuccess")
                }

                override fun onError(anError: ANError?) {
                    Log.i("MovieFailed", anError?.message.toString())
                }
            })
    }

    fun getTv(movieAPICallback: MovieAPICallback) {
        AndroidNetworking.get(BASE_URL_TV_SERIES)
            .addQueryParameter("api_key", API_KEY)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val list = jsonParse(response!!, "TV")
                    movieAPICallback.onResponse(list)
                    Log.i("TVSuccess", "TVSuccess")
                }

                override fun onError(anError: ANError?) {
                    Log.i("TVFailed", anError?.message.toString())
                }
            })
    }

    private fun jsonParse(json: JSONObject, category: String): List<DataResponse> {
        val list = mutableListOf<DataResponse>()
        val array = json.getJSONArray("results")
        for (i in 0 until array.length()) {
            val item = array.getJSONObject(i)
            if (category == "MOVIES") {
                val genre = GenreUtils.getGenre(item, "MOVIES")
                list.add(
                    DataResponse(
                        item.getInt("id"),
                        item.getString("original_title"),
                        genre,
                        item.getDouble("vote_average"),
                        item.getString("overview"),
                        "https://image.tmdb.org/t/p/w500${item.getString("poster_path")}",
                        "https://image.tmdb.org/t/p/w500${item.getString("backdrop_path")}"
                    )
                )
            } else {
                val genre = GenreUtils.getGenre(item, "TV")
                list.add(
                    DataResponse(
                        item.getInt("id"),
                        item.getString("original_name"),
                        genre,
                        item.getDouble("vote_average"),
                        item.getString("overview"),
                        "https://image.tmdb.org/t/p/w500${item.getString("poster_path")}",
                        "https://image.tmdb.org/t/p/w500${item.getString("backdrop_path")}"
                    )
                )
            }
        }
        return list
    }

}