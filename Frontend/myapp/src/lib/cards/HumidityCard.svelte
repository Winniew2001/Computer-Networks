<script>
    import Card from "./Card.svelte";
    import {chartConfig, singleDatasetConfig, singleScaleConfig} from "../scripts/chart-configs";
</script>

<Card
        config = {chartConfig(singleScaleConfig("Humidity %"), singleDatasetConfig("Humidity", false))}
        isTemp = {false}
        type = "Recommended Humidity"
        unit = "30-50%"
        title = "Humidity"
/>