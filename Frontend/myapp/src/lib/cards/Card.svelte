<script>
    import Temperature from "../charts/Chart.svelte";
    import CardButton from "../CardButton.svelte";
    import Statistics from "../Statistics.svelte";

    export let type = "type";
    export let unit = "unit";
</script>

<div class="card">
    <h2>{type}</h2>
    <div class="buttons">
        <CardButton>Day</CardButton>
        <CardButton>Week</CardButton>
        <CardButton>Month</CardButton>
    </div>
    <div class="graph-container">
        <Temperature />
    </div>
    <div class="statistics">
        <Statistics title="Average {type}" number="40{unit}"/>
        <Statistics title="Highest Recorded {type}" number="86{unit}"/>
        <Statistics title="Lowest Recorded {type}" number="13{unit}"/>
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

    .buttons{
        display: flex;
        justify-content: flex-end;
    }
</style>