<script>
    import Temperature from "./Chart.svelte";
    import CardButton from "./CardButton.svelte";
    import Statistics from "./Statistics.svelte";
</script>

<div class="card">
    <h2>Humidity</h2>
    <div class="buttons">
        <CardButton>Day</CardButton>
        <CardButton>Week</CardButton>
        <CardButton>Month</CardButton>
    </div>
    <div class="graph-container">
        <Temperature />
    </div>
    <div class="statistics">
        <Statistics title="Average Humidity" number="40%"/>
        <Statistics title="Highest Recorded Humidity" number="86%"/>
        <Statistics title="Lowest Recorded Humidity" number="13%"/>
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