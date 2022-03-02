package com.zenika.handson.spring.controller.web

import com.zenika.handson.spring.entities.Weather
import com.zenika.handson.spring.repositories.CitiesRepository
import com.zenika.handson.spring.services.WeatherService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import kotlin.streams.toList


@Controller
class AgenciesPage(val citiesRepository: CitiesRepository, val weatherService: WeatherService) {

    @GetMapping("/")
    fun homepage(): ModelAndView {
        val mv = ModelAndView("agences")
        val weatherByAgencies: List<Pair<String, Weather?>> = citiesRepository.findAll()
                .parallelStream()
                .map {
                    it.name to weatherService.getDailyWeatherForCity(it.name)?.get(0)
                }
                .toList()
        mv.addObject("agences", weatherByAgencies)
        return mv
    }
}
