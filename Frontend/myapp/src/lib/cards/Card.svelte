<script>
    import Chart from "../charts/Chart.svelte";
    import Statistics from "../Statistics.svelte";

    export let config;

    export let title = "title";
    export let type = "type";
    export let unit = "unit";
    export let isTemp = true;
    export let isDewPoint = false;
    export let isMulti = false;
</script>

<div class="card">
    <h2>{title}</h2>
    <div class="graph-container">
        <Chart chartConfig = "{config}" isTemp = {isTemp} isMulti = {isMulti} isDewPoint = {isDewPoint}/>
    </div>
    <div class="statistics">
        <Statistics title="{type}" number="{unit}"/>
    </div>
</div>


<style>
    .card{
        background: white;
        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
        padding: 20px 80px;
        margin-left: 3rem;
        margin-right: 3rem;
    }

    .statistics{
        display: flex;
        justify-content: space-evenly;
    }
</style>
