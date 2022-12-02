<canvas id="myChart" width="400" height="100" bind:this={ctx}></canvas>
<script>
    import {onMount} from 'svelte'
    import Chart from 'chart.js/auto';

    let ctx = document.getElementById('myChart');

    const data = {
        labels: ["1AM", "2AM", "3AM", "4AM", "5AM", "6AM", "7AM"],
        datasets: [{
            label: "Temperature",
            data: [13, 8, 3, 19, 4, 7, 14],
        }]
    }

    const test = [1,2,3,4,5,6,7,8,9];

    onMount(
        async ()=> {
            const myChart = new Chart(ctx, {
                type: "line",
                data: data,
            })
        }
    );
</script>
