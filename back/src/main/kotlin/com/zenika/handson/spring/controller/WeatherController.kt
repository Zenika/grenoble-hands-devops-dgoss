package com.zenika.handson.spring.controller

import com.zenika.handson.spring.controller.vm.CityWithWeather
import com.zenika.handson.spring.entities.Weather
import com.zenika.handson.spring.repositories.CitiesRepository
import com.zenika.handson.spring.services.WeatherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

@RestController
@RequestMapping("/cities")
class WeatherController(private val weatherService: WeatherService, private val citiesRepository: CitiesRepository) {

    @GetMapping("/{name}/weather/daily")
    fun getDailyWeather(@PathVariable name: String) = weatherService.getDailyWeatherForCity(name)
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.notFound().build()

    @GetMapping("/{name}/weather/hourly")
    fun getHourlyWeather(@PathVariable name: String) = weatherService.getHourlyWeatherForCity(name)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/weather/hourly")
    fun getCitiesWeather() = citiesRepository.findAll().parallelStream()
                .map {
                    val weather = weatherService.getHourlyWeatherForCity(it.name)!![0]
                    CityWithWeather(it.name, weather)
                }
                .toList()
}
