/*
 *  Communication with Mqqt broker.
 *  Exports getters for the relevant fields.
 */


let temperature = 0;
let humidity = 0;

let client = new Paho.Client("localhost", Number(1883), "clientId");

// set callback handlers
client.onConnectionLost = onConnectionLost;
client.onMessageArrived = onMessageArrived;

// connect the client
client.connect({onSuccess: onConnect});


// called when the client connects
function onConnect() {
    client.subscribe("aronshouse/sornesvaagen/kitchen/temperature/gr30/0");
    client.subscribe("aronshouse/sornesvaagen/kitchen/humidity/gr30/0");
}

// called when the client loses its connection
function onConnectionLost(responseObject) {
    if (responseObject.errorCode !== 0) {
        console.log(responseObject.errorMessage);
    }
}

// called when a message arrives
function onMessageArrived(message) {
    if (message.topic.includes("temperature", 11)) temperature = message.payloadString;
    if (message.topic.includes("humidity", 8)) humidity = message.payloadString;
}

export function sleep(time) {
    return new Promise(resolve => setTimeout(resolve, time))
}

async function getValuesFromServer() {
    while (true) {
        await sleep(1000).then(() => onConnect());
    }
}
getValuesFromServer();

export function getTemperature() {
    return temperature;
}

export function getHumidity() {
    return humidity;
}
