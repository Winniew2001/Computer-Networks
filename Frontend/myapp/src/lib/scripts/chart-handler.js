import { getHumidity, getTemperature, sleep } from "./mqtt-communication";
import {retrieveDewPoint} from "./data-processing";

import { DateTime } from "luxon";

function addDataToMultiChart(chart, label, data1, data2) {
    chart.data.labels.push(label);
    chart.data.datasets[0].data.push(data1);
    chart.data.datasets[1].data.push(data2);
    chart.update();
}

function addChartData(chart, label, data) {
    chart.data.labels.push(label);
    chart.data.datasets.forEach((dataset) => {
        dataset.data.push(data);
    });
    chart.update();
}

export async function readMultiData(chart, isDewDrop = false) {
    while (true) {
        await sleep(1000).then(() => {
                let temperature = +getTemperature();
                let data = isDewDrop ? +retrieveDewPoint() : +getHumidity();
                let newData = [
                    temperature, // y   =  temp
                    data,    // y1  =  other
                ];

                if (data !== undefined && temperature !== undefined && chart !== undefined) {
                    addDataToMultiChart(
                        chart,
                        DateTime.now().toLocaleString(DateTime.TIME_WITH_SECONDS),
                        newData[0],
                        newData[1],
                    );
                }
            }
        );
    }
}

export async function readSingleData(chart, isTemp) {
    while (true) {
        await sleep(1000).then(() => {
                let data = isTemp ? +getTemperature() : +getHumidity();

                if (data !== undefined && chart !== undefined) {
                    addChartData(
                        chart,
                        DateTime.now().toLocaleString(DateTime.TIME_WITH_SECONDS),
                        data,
                    );
                }
            }
        );
    }
}
