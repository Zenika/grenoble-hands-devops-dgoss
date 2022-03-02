package com.zenika.handson.spring.repositories.http

import com.zenika.handson.spring.entities.HourlyWeather
import com.zenika.handson.spring.entities.Weather
import com.zenika.handson.spring.entities.WeatherState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Weather7TimerMapper {

    companion object {

        fun toDomain(dailyWeather7Timer: DailyWeather7Timer): List<Weather> {
            return dailyWeather7Timer.dataseries.map {
                Weather(
                        day = LocalDate.parse(it.date, DateTimeFormatter.ofPattern("yyyyMMdd")),
                        weather = getWeatherState(it.weather),
                        temperatureMax = it.temp2m.max,
                        temperatureMin = it.temp2m.min
                )
            }
        }

        fun toDomain(dailyWeather7Timer: HourlyWeather7Timer): List<HourlyWeather> {
            val current = LocalDateTime.now()
            return dailyWeather7Timer.dataseries.map {
                HourlyWeather(
                        date = current.plusHours(it.timepoint.toLong()),
                        weather = getWeatherState(it.weather),
                        temperature = it.temp2m,
                )
            }
        }

        fun getWeatherState(weather: String): WeatherState {
            return when (weather) {
                "clear", "clearday", "clearnight" -> WeatherState.sunny
                "pcloudy", "pcloudyday", "pcloudynight", "windy", "windyday", "windynight", "humid" -> WeatherState.partly_cloudy
                "mcloudy", "mcloudynight", "mcloudyday" -> WeatherState.cloudy_s_sunny
                "cloudy", "cloudyday", "cloudynight" -> WeatherState.cloudy
                "fog", "fogday", "fognight", "humidnight", "humidday" -> WeatherState.fog
                "lightrain", "lightrainday", "lightrainnight", "rain", "rainday", "rainnight" -> WeatherState.rain
                "oshower", "oshowerday", "oshowernight" -> WeatherState.rain_s_sunny
                "ishower", "ishowerday", "ishowernight" -> WeatherState.sunny_s_rain
                "lightsnow", "lightsnowday", "lightsnownight" -> WeatherState.snow_light
                "snow", "snowday", "snownight" -> WeatherState.snow
                "rainsnow", "rainsnowday", "rainsnownight" -> WeatherState.sleet
                "ts", "tstorm", "tsrrain", "tsnight", "tsday" -> WeatherState.thunderstorms
                else -> {
                    println("$weather is unknown")
                    WeatherState.unknown
                }
            }
        }

    }
}
