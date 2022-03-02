import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CityComponent} from "./city.component";
import {LMapComponent} from "../../shared/components/lmap/lmap.component";
import {DegreePipe} from "../../shared/pipes/degree.pipe";
import {RouterModule} from "@angular/router";


@NgModule({
    declarations: [
        CityComponent,
        LMapComponent,
        DegreePipe,
    ],
    imports: [
        CommonModule,
        RouterModule.forChild([
            { path: ':cityName', component: CityComponent}
        ])
    ]
})
export class CityModule {
}
