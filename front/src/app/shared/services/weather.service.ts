import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";
import {Weather} from "../model/weather";
import {WeatherDetailed} from "../model/weather-detailed";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(protected http: HttpClient) {
  }

  getCityNextWeekWeather(city: string): Observable<Weather[]> {
    return this.http.get<any>(`/api/cities/${city}/weather/daily`)
  }

  getCityDetailedWeather(city: string): Observable<WeatherDetailed[]> {
    return this.http.get<any>(`/api/cities/${city}/weather/hourly`)
  }
}
