<script>
  let x = 0;

  let client = new Paho.Client("localhost", Number(1883) ,"clientId");

  // set callback handlers
  client.onConnectionLost = onConnectionLost;
  client.onMessageArrived = onMessageArrived;

  // connect the client
  client.connect({onSuccess:onConnect});


  // called when the client connects
  function onConnect() {
    client.subscribe("aronshouse/sornesvaagen/kitchen/humidity/gr30/0");
    //setTimeout(onConnect, 2000);
  }

  // called when the client loses its connection
  function onConnectionLost(responseObject) {
    if (responseObject.errorCode !== 0) {
      console.log(responseObject.errorMessage);
    }
  }

  // called when a message arrives
  function onMessageArrived(message) {
    console.log(message.payloadString);
    x = message.payloadString;
  }

  function sleep(time){
    return new Promise(resolve => setTimeout(resolve, time))
  }

  async function displayRandomNumber() {
    while (true) {
      await sleep(1000).then(() => onConnect());
    }
  }

  displayRandomNumber();
</script>

<p>
  temperature is {x}
</p>
