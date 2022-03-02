package com.zenika.handson.spring.entities

import java.time.LocalDate
import java.time.LocalDateTime

data class Weather(val day: LocalDate, val weather: WeatherState, val temperatureMax: Double, val temperatureMin: Double)
data class HourlyWeather(val date: LocalDateTime, val weather: WeatherState, val temperature: Double)

enum class WeatherState {
    partly_cloudy,
    cloudy,
    rain_s_sunny,
    sunny_s_snow,
    snow_s_sunny,
    sunny_s_rain,
    sunny,
    snow_light,
    fog,
    cloudy_s_sunny,
    sunny_s_cloudy,
    sleet,
    rain,
    snow,
    thunderstorms,
    unknown,
}
