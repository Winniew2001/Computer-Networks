<script>
  import TemperatureCard from "./lib/cards/TemperatureCard.svelte";
  import HumidityCard from "./lib/cards/HumidityCard.svelte";
  import TemperatureAndHumidityCard from "./lib/cards/TemperatureAndHumidityCard.svelte";
  import TemperatureAndDewPointCard from "./lib/cards/TemperatureAndDewPointCard.svelte";
</script>


<main>
  <TemperatureAndDewPointCard />
  <div class="aaa"></div>
  <TemperatureCard />
  <div class="aaa"></div>
  <HumidityCard />
  <div class="aaa"></div>
  <TemperatureAndHumidityCard />
</main>

<style>
  .aaa{
    margin: 50px;
  }
</style>
