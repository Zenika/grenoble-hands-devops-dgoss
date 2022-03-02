package com.zenika.handson.spring.controller.vm

import com.zenika.handson.spring.entities.HourlyWeather

data class CityWithWeather(val name: String, val weather: HourlyWeather)
