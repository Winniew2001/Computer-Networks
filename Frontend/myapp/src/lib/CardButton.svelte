<button>
    <slot></slot>
</button>

<style>
button{
    display: inline-block;
    padding:0.3em 1.2em;
    margin: 0 0.3em 0.3em 0;
    border-radius: 2em;
    box-sizing: border-box;
    text-decoration: none;
    font-weight: 300;
    border: none;
    color:#FFFFFF;
    background-color: #57a9ff;
    text-align: center;
    transition: all 0.2s;
}

button:hover{
    background-color: #4190e3;
}
</style>