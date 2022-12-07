<script>
    import { onMount } from 'svelte';
    import {readMultiData, readSingleData } from "../scripts/chart-handler";

    import Chart from 'chart.js/auto';

    export let chartConfig;

    let ctx;
    let chart;
    export let isMulti = false;
    export let isTemp = true;
    export let isDewPoint = false;

    async function makeChart() {
        chart = new Chart(ctx, chartConfig);
        return chart;
    }

    onMount(
        async () => {
            await makeChart();
            if (isMulti) await readMultiData(chart, isDewPoint);
            else await readSingleData(chart, isTemp);
        }
    );
</script>

<canvas width="400" height="100" bind:this={ctx}></canvas>