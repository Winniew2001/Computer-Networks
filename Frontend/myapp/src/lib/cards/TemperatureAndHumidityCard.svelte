<script>
    import Card from "./Card.svelte";
    import {
        chartConfig,
        temperatureAndHumidityDatasetsConfiguration,
        temperatureAndHumidityScalesConfiguration
    } from "../scripts/chart-configs";
</script>

<Card
        config = {chartConfig(temperatureAndHumidityScalesConfiguration, temperatureAndHumidityDatasetsConfiguration)}
        isMulti = {true}
        isDewPoint = {true}
        type = ""
        unit = ""
        title = "Temperature and Humidity"
/>