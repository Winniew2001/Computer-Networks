<script>
    import Card from "./Card.svelte";
    import {chartConfig, singleDatasetConfig, singleScaleConfig} from "../scripts/chart-configs";
</script>

<Card config = {chartConfig(singleScaleConfig("Temperature °C"), singleDatasetConfig("Temperature"))}
      type = "Recommended Temperature"
      unit = "~20C°"
      title = "Temperature"
/>