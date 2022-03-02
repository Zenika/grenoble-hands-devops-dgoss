package com.zenika.handson.spring.repositories.http

data class HourlyWeather7Timer(val dataseries: List<DataSeries>) {
    data class DataSeries(val timepoint: Int, val temp2m: Double, val weather: String, val wind10m: Wind10m)
    data class Wind10m(val direction: String, val speed: Double)
}
