import {Component, OnInit} from '@angular/core';
import {CitiesService} from "../../shared/services/cities.service";
import {Observable} from "rxjs";
import {CityWithWeather} from "../../shared/model/city-with-weather";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

    cities$: Observable<CityWithWeather[]>;

    constructor(protected citiesService: CitiesService) {
    }

    ngOnInit(): void {
        this.cities$ = this.citiesService.getCities();
    }

}
