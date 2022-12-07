<script>
    import Card from "./Card.svelte";
    import {
        chartConfig,
        temperatureAndDewPointDatasetsConfiguration,
        temperatureAndDewPointScalesConfiguration,
    } from "../scripts/chart-configs";
</script>

<Card
        config = {chartConfig(temperatureAndDewPointScalesConfiguration, temperatureAndDewPointDatasetsConfiguration)}
        isMulti = {true}
        isDewPoint = {true}
        type = "Temperature and Dew Point"
        unit = "Temperature should not be lower then dew point!"
        title = "Temperature and Dew Point"
/>