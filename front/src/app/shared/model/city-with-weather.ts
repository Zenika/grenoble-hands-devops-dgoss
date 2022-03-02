import {City} from "./city";
import {WeatherDetailed} from "./weather-detailed";

export interface CityWithWeather extends City {
    weather: WeatherDetailed
}
