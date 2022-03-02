import {Component, OnInit} from '@angular/core';
import {WeatherService} from "../../shared/services/weather.service";
import {Observable} from "rxjs";
import {Weather} from "../../shared/model/weather";
import {Coordinates} from "../../shared/model/coordinates";
import {ActivatedRoute} from "@angular/router";
import {map, mergeMap} from "rxjs/operators";
import {CitiesService} from "../../shared/services/cities.service";
import {WeatherDetailed} from "../../shared/model/weather-detailed";

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.scss']
})
export class CityComponent implements OnInit {

  cityName$: Observable<string>;
  cityWeather$: Observable<Weather[]>;
  cityWeatherDetailed$: Observable<WeatherDetailed[]>;
  cityCoords$: Observable<Coordinates>;
  degree: 'C' | 'F' = 'C';
  mode: 'simple' | 'detailed' = "simple";

  constructor(protected weatherService: WeatherService, protected citiesService: CitiesService, protected route: ActivatedRoute) {
  }


  ngOnInit(): void {
    this.cityName$ = this.route.params.pipe(
      map(params => params.cityName)
    )
    this.cityCoords$ = this.cityName$.pipe(
      mergeMap(cityName => this.citiesService.getCityPosition(cityName))
    )
    this.cityWeather$ = this.cityName$.pipe(
      mergeMap(cityName => this.weatherService.getCityNextWeekWeather(cityName)),
    )
    this.cityWeatherDetailed$ = this.cityName$.pipe(
      mergeMap(cityName => this.weatherService.getCityDetailedWeather(cityName)),
    )
  }

}
