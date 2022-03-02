package com.zenika.handson.spring.repositories.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.zenika.handson.spring.entities.City
import com.zenika.handson.spring.entities.HourlyWeather
import com.zenika.handson.spring.entities.Weather
import com.zenika.handson.spring.repositories.WeatherRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Repository
import org.springframework.web.client.getForObject


@Repository
class WeatherRepository7Timer(
  restTemplateBuilder: RestTemplateBuilder,
  val objectMapper: ObjectMapper,
  @Value("\${weather.url}") val weatherUrl: String) :
        WeatherRepository {
    private val restTemplate = restTemplateBuilder.build()

    override fun getDailyWeather(city: City): List<Weather> {
        return getResponse(city, "civillight")
                .let { objectMapper.readValue(it, DailyWeather7Timer::class.java) }
                .let { Weather7TimerMapper.toDomain(it) }
    }

    override fun getHourlyWeather(city: City): List<HourlyWeather> {
        return getResponse(city, "civil")
                .let { objectMapper.readValue(it, HourlyWeather7Timer::class.java) }
                .let { Weather7TimerMapper.toDomain(it) }
    }

    private fun getResponse(city: City, product: String) =
            restTemplate.getForObject<String>("$weatherUrl/$product.php?lon=${city.position.longitude}&lat=${city.position.latitude}&ac=0&unit=metric&output=json&tzshift=0")
}
