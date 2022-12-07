# [Home monitoring for temperature and humidity for mold]

This is a project related to our course IDATA2304 Computer networks. 

## Abstract

The environment around us directly impacts our health. High humidity and temperatures in houses can have adverse effects on 
one’s health but also the house itself. When the humidity and temperatures are high, it could create the perfect 
environment for mold to thrive. With modern technology, we can monitor these factors that are often overlooked. Our 
solution is a clean and minimalistic informational web view of graphs describing a home environment.  

## Introduction

Our proposed solution is primarily targeted towards private homes, since people generally spend a good portion of day-to-
day life at home, but also value the condition of the house. 

For many, the environmental details of their living space tend to go unnoticed. While changes in temperature easily be felt 
by the human body, the level of humidity can be far more difficult to detect. Damage to a private home can also lead to 
financial loss should there be a need for repairs, as such we believe that many home-owners would like an easy way to 
monitor the environment of their house.  

The following sections will be structured into different sections. Firstly, we will go over some of the [Theory and 
technologies](#theory-and-technology) behind our solution, followed by our work process in [Methodology](#methodology). 
Then we present our [Results](#results), followed by a [Discussion](#discussion) around the outcome. Lastly, we will summarize our work in [Conclusion and future work](#concluision-and-future-work). 

# Theory and technology

Our main goal for this project is to send data from a client to a server, then receive this data in another client. We 
first used MQTT over TCP to connect to the public broker, but eventually we switched over to WebSocket as that is what is 
supported in web browsers. So, we implemented socket connections between the client simulating data and our MQTT broker, as 
well as between the MQTT broker and our Svelte/JavaScript web application. 

MQTT is a lightweight machine to machine communication protocol based on TCP, now it also has socket support. TCP is a 
protocol for reliable data transfer, and IP is an addressing protocol. Sockets are used as a bi-directional communication 
channel that stays open until you close it. Lastly, we used some HTTP web protocol for our live development server when 
making our front-end application 

MQQT using WebSocket's was our primary choice due to how cleanly it implements extended communications, and as we are 
sending/receiving data around twice every second per application it made sense for us. 

Since we are interested in helping homeowners monitor the temperature and humidity in their houses, it becomes important to 
understand the conditions in which mold thrive best.  

Humidity and dew point are both measurements of the amount of water in the air. The difference is that dew point is defined 
as the lowest temperature at which dewdrops will start forming, based on how much water vapor is currently in the air. Once 
the temperature becomes relatively close to the dewpoint, dew will start forming. 

Mold is most comfortable at the same temperature as we prefer, which is usually around 20 C°. This makes it for us to 
prevent mold in the aspect of changing the temperature, but what we can do is prevent the humidity levels from becoming too 
high. Since mold thrives best in environments that are damp and humid, it becomes important to prevent condensation from 
forming inside the house. It is said that the ideal humidity range of humidity levels indoors lies between 30-50%. We used 
a formula to calculate the dew point given temperature and humidity, this can be found on the wikipedia page about dew 
point under simple approximations. 

When simulating the data, we wanted to make sure it would seem realistic. This means that the temperature or humidity could 
not suddenly jump from a very high number to a very low number, but instead slowly incrementally build upwards or 
downwards. 

Since our data would be temperature, humidity and dew point, we decided to use a JavaScript library called Chart.js for the 
visualization of our data.  

This project is connected to the subject Web technologies we have both previously had, from learning how to write markup, 
style with css, and allowing interactive web pages with JavaScript. 

# Methodology

The first step to solving a problem was finding a problem to solve. We had concluded that the data we wanted to simulate 
was temperature and humidity. With this in mind, we looked for a problem related to these factors. The first problem we 
evaluated was the correlation between humidity, temperature and skin problems. After researching around that topic, we 
concluded that the topic was out of our scope, due to the lack of credible research.  

The second topic we investigated was mold growth in homes. Having heard of mold infections in the past, and knowing this is 
a real problem, we decided to look into this more. Finding more substantial information about this, we decided to proceed 
with this topic.  

Our first goal was to create dummy sensors that would simulate temperature data and humidity data. In order to see what was 
being simulated, we printed out the result of the simulation in the terminal of the idea. After this was finished, we made 
a client in Java that would connect to the MQTT broker provided, and check if our data was being sent also using the 
terminal and prints.  

Once we knew that the client was properly sending data to the broker, our next goal was to start visualizing the data we 
would get. As we had previously worked with web technologies and made a website, we decided that we wanted to make one for 
this project, utilizing the chart.js charting library for creating the graphs we needed. As a first step to learning about 
graphs, we decided to make static line graphs, before proceeding further with the project. When coding for this website, we 
tried to make components that would be reusable, but this was not always possible. 

The next big goal was to create our own MQTT broker but in Java. We utilized an API called Vert.x, which provides a server 
that can handle connections, communications and message exchange with remote MQTT clients. This is an imitation of a real 
MQTT broker. Once this was finished, we then had to make sure that data sent from the Java client would go through the 
broker, then to the website. The API would tell us if we had successfully received data from our client. We had some issues 
between sending data from the broker up towards the WebSocket. Since our broker was not a true MQTT broker we had to 
manually request the data every time we wanted it, we did this by simply re-subscribing to the topic every time we wanted 
it, almost resembling a rest API. For displaying the data, we used the charts.js library to make line-graphs since they are 
well suited for displaying changes over time. For the dew point graph, we also needed to process the temperature and 
humidity with a mathematical formula to get the dew point values to display. Additionally, we made a class for holding 
graph configurations in order to clean up the code. 

# Results

![dasdasdasdas](https://user-images.githubusercontent.com/77609835/206168845-cb298392-0313-4cf2-967d-3b45c47c562e.png)
We ended up with a web application for displaying the data we collected along with one processed form of it. This web 
application communicates with an imitation MQQT broker that sends it the data it last received from its client that is 
providing it with simulated data. This client we made is a small application sending numbers that are generated within a 
range to the MQQT broker. These numbers are supposed to represent possible values a sensor could have generated. 

![fdgftgfdgfd](https://user-images.githubusercontent.com/77609835/206168882-2eb0810f-b411-4feb-97ae-7bca2a7933e4.PNG)
The blue rectangle represents the java application sending data to the broker, the broker being the rounded rectangle that 
sends the data to the web application, the purple circle when the web application requests it. 

# Discussion

While sprints were good goals, in the end we don’t think that they are a good representation of our workflow, especially 
because our project was always changing under development and priorities re-ordered. Overall, we are not the happiest with 
the result as it was rather rushed and squeezed in-between other projects. 

As for what we liked about the project, the graphs we made, the mock MQTT server and code structure in general were high 
during most of the project. 

# Conclusion and future work

Overall, we managed to create a web page that displays data regarding the temperature, humidity and dew point from data 
created by dummy sensors over a MQTT broker. We managed to reach a completed project, but some lower code quality was 
introduced towards the end, and we could probably have commented more.  

Possible future work would be to implement a notification system, make the applications more informative and make expanding 
upon the existing codebase easier. Lastly, time management is always a skill that needs improving. 


# References
https://en.wikipedia.org/wiki/Dew_point (Last edited on 6th of December 2022) 
