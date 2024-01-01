package com.example.weatherapp.data

import com.example.weatherapp.data.forecastModels.Forecast
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.pollutionModels.PollutionData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather?")
    suspend fun getCurrentWeather(
        @Query("q") city : String,
        @Query("units") units : String,
        @Query("appid") apiKey : String,
    ):Response<CurrentWeather>

    @GET("forecast?")
    suspend fun getForecast(
        @Query ("q") city: String,
        @Query("units") units : String,
        @Query("appid") apiKey : String,
    ) :Response<Forecast>

    @GET("air_pollution?")
    suspend fun getPollution(
        @Query ("lat") lat: Double,
        @Query ("lon") lon: Double,
        @Query("units") units : String,
        @Query("appid") apiKey : String,
    ): Response<PollutionData>
}