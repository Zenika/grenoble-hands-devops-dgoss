package com.zenika.handson.spring.repositories.inmemory

import com.zenika.handson.spring.entities.City
import com.zenika.handson.spring.entities.HourlyWeather
import com.zenika.handson.spring.entities.Weather
import com.zenika.handson.spring.entities.WeatherState
import com.zenika.handson.spring.repositories.WeatherRepository
import java.time.LocalDate


class WeatherRepositoryInMemory : WeatherRepository {
    override fun getDailyWeather(city: City): List<Weather> {
        return listOf(
            Weather(LocalDate.of(2021, 2, 23), WeatherState.cloudy, 28.0, 27.0)
        )
    }

    override fun getHourlyWeather(city: City): List<HourlyWeather> {
        TODO("Not yet implemented")
    }
}
