<script>
  let x = 0;

  function sleep(time){
    return new Promise(resolve => setTimeout(resolve, time))
  }

  async function displayRandomNumber() {
    while (true) {
      await sleep(5000).then(() => printRandomNumber());
    }
  }

  displayRandomNumber();

  function printRandomNumber(){
    x = (Math.random() * 100) + 1;
    console.log(x);
  }
</script>

<p>
  temperature is {x}
</p>
