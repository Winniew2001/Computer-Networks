export function chartConfig(scalesConfiguration, dataConfiguration) {
    return {
        type: "line",
        data: dataConfiguration,
        options: {
            animation: false,
            responsive: true,
            stacked: false,
            scales: scalesConfiguration,
        }
    }
}

export function singleDatasetConfig(dataLabel, isRed = true) {
    let lineBorderColor = isRed ? 'rgba(228, 33, 90, 0.6)' : 'rgba(68, 207, 223, 0.6)';
    let lineColor = isRed ? 'rgba(228, 33, 90, 1)' : 'rgba(68, 207, 223, 1)';
    return {
        labels: [],
        datasets: [
            {
                label: dataLabel,
                data: [],
                borderColor: lineBorderColor,
                backgroundColor: lineColor,
                yAxisID: 'y',
            }
        ]
    }
}

export function singleScaleConfig(yLabel) {
    return {
        x: {
            ticks: {
                maxRotation: 60,
                minRotation: 60,
            },
            display: true,
            title: {
                display: true,
                text: 'Time',
            }
        },
        y: {
            type: 'linear',
            display: true,
            title: {
                display: true,
                text: yLabel,
            },
            position: 'right'
        },
    }
}

export const temperatureAndHumidityScalesConfiguration = {
    x: {
        ticks: {
            maxRotation: 60,
            minRotation: 60,
        },
        display: true,
        title: {
            display: true,
            text: 'Time',
        }
    },
    y: {
        type: 'linear',
        display: true,
        title: {
            display: true,
            text: 'Temperature C°',
        },
        position: 'left'
    },
    y1: {
        type: 'linear',
        display: true,
        title: {
            display: true,
            text: 'Humidity %',
        },
        position: 'right',

        //grid line settings
        grid: {
            drawOnChartArea: false,
        },
    },
}

export const temperatureAndDewPointScalesConfiguration = {
    x: {
        ticks: {
            maxRotation: 60,
            minRotation: 60,
        },
        display: true,
        title: {
            display: true,
            text: 'Time',
        }
    },
    y: {
        type: 'linear',
        display: true,
        title: {
            display: true,
            text: 'Temperature C°',
        },
        position: 'left'
    },
}

export const temperatureAndHumidityDatasetsConfiguration = {
    labels: [],
    datasets: [
        {
            label: "Temperature",
            data: [],
            borderColor: 'rgba(228, 33, 90, 0.6)',
            backgroundColor: 'rgba(228, 33, 90, 1)',
            yAxisID: 'y',
        },
        {
            label: "Humidity",
            data: [],
            borderColor: 'rgba(68, 207, 223, 0.6)',
            backgroundColor: 'rgba(68, 207, 223, 1)',
            yAxisID: 'y1'
        }
    ]

}

export const temperatureAndDewPointDatasetsConfiguration = {
    labels: [],
    datasets: [
        {
            label: "Temperature",
            data: [],
            borderColor: 'rgba(228, 33, 90, 0.6)',
            backgroundColor: 'rgba(228, 33, 90, 1)',
            yAxisID: 'y',
        },
        {
            label: "Dew Point",
            data: [],
            borderColor: 'rgba(200, 207, 223, 0.6)',
            backgroundColor: 'rgba(200, 207, 223, 1)',
            yAxisID: 'y'
        }
    ]

}