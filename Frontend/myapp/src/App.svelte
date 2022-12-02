<script>
  import Counter from './lib/Counter.svelte'
  import TemperatureCard from "./lib/cards/TemperatureCard.svelte";
  import HumidityCard from "./lib/cards/HumidityCard.svelte";
</script>


<main>
  <div class="card">
    <Counter />
  </div>
  <TemperatureCard />
  <div class="aaa">
  </div>
  <HumidityCard />
</main>

<style>
  .aaa{
    margin: 50px;
  }
</style>
