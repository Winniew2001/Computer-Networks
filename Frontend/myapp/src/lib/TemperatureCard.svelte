<script>
    import Temperature from "./Chart.svelte";
    import CardButton from "./CardButton.svelte";
    import Statistics from "./Statistics.svelte";
</script>

<div class="card">
    <h2>Temperature</h2>
    <div class="buttons">
        <CardButton>Day</CardButton>
        <CardButton>Week</CardButton>
        <CardButton>Month</CardButton>
    </div>
    <div class="graph-container">
        <Temperature />
    </div>
    <div class="statistics">
        <Statistics title="Average Temperature" number="18.0° C"/>
        <Statistics title="Highest Recorded Temperature" number="4028.8° C"/>
        <Statistics title="Lowest Recorded Temperature" number="-20.1° C"/>
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