import {Injectable} from '@angular/core';
import {Coordinates} from "../model/coordinates";
import {HttpClient} from "@angular/common/http";
import {forkJoin, Observable} from "rxjs";
import {map, mergeMap} from "rxjs/operators";
import {WeatherService} from "./weather.service";
import {City} from "../model/city";
import {CityWithWeather} from "../model/city-with-weather";

@Injectable({
    providedIn: 'root'
})
export class CitiesService {

    constructor(protected http: HttpClient, private weatherService: WeatherService) {
    }

    getCities(): Observable<CityWithWeather[]> {
        return this.http.get<CityWithWeather[]>(`/api/cities/weather/hourly`)
    }

    getCityPosition(cityName): Observable<Coordinates> {
        return this.http.get<City>(`/api/cities/${cityName}`).pipe(map(city => city.position))
    }

    addCity(city: { name: string, latitude: number, longitude: number }) {
        return this.http.post<City>(`/api/cities`, {
            name: city.name,
            position: {latitude: city.latitude, longitude: city.longitude}
        })
    }
}
