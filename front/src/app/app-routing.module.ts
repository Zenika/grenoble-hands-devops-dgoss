import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./modules/home/home.component";

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'create', loadChildren: () => import("./modules/create/create.module").then(m => m.CreateModule)},
    {path: 'city', loadChildren: () => import("./modules/city/city.module").then(m => m.CityModule)},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
