package com.zenika.handson.spring.config

import com.zenika.handson.spring.repositories.CitiesRepository
import com.zenika.handson.spring.services.WeatherService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class CacheConfig(val citiesRepository: CitiesRepository, val weatherService: WeatherService): ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        runBlocking {
            citiesRepository.findAll()
                    .forEach() {
                        launch {
                            val jobDaily = launch {
                                weatherService.getDailyWeatherForCity(it.name)
                            }
                            val jobHourly = launch {
                                weatherService.getHourlyWeatherForCity(it.name)
                            }
                            jobDaily.join()
                            jobHourly.join()
                            println("Fetch $it weather")
                        }
                    }
        }
    }
}
