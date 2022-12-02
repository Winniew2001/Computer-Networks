<canvas id="myChart" width="400" height="100" bind:this={ctx}></canvas>
<script>
    import {onMount} from 'svelte'
    import Chart from 'chart.js/auto';

    let ctx = document.getElementById('myChart');

    const data = {
        labels: ["1AM", "2AM", "3AM", "4AM", "5AM", "6AM", "7AM"],
        datasets: [
            {
                label: "Temperature",
                data: [13, 8, 3, 19, 4, 7, 14],
                borderColor: 'rgba(228, 33, 90, 0.6)',
                backgroundColor: 'rgba(228, 33, 90, 1)',
                yAxisID: 'y',
            },
            {
                label: "Humidity",
                data: [21, 20, 25, 24, 21, 20, 19, 22],
                borderColor: 'rgba(68, 207, 223, 0.6)',
                backgroundColor: 'rgba(68, 207, 223, 1)',
                yAxisID: 'y1'
            }
        ]
    }

    onMount(
        async () => {
            const myChart = new Chart(ctx, {
                type: "line",
                data: data,
                options: {
                    responsive: true,
                    stacked: false,
                    scales: {
                        x: {
                            display: true,
                            title: {
                                display: true,
                                text: 'Time',
                            }
                        },
                        y: {
                            type: 'linear',
                            display: true,
                            title: {
                                display: true,
                                text: 'Temperature C°',
                            },
                            position: 'left'
                        },
                        y1: {
                            type: 'linear',
                            display: true,
                            title: {
                                display: true,
                                text: 'Humidity %',
                            },
                            position: 'right',

                            //grid line settings
                            grid: {
                                drawOnChartArea: false,
                            },
                        },
                    }
                }
            })
        }
    );
</script>