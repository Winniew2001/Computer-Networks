import { getHumidity, getTemperature } from "./mqtt-communication";

let temperature;
let humidity;
let dewPoint;
let isGood;

export function retrieveDewPoint(){
    temperature = getTemperature();
    humidity = getHumidity();

    dewPoint = temperature - ((100 - humidity) / 5);
    isGood = temperature > dewPoint;

    return dewPoint;
}

export function getIsGood(){
    return isGood;
}
