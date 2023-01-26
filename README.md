# Twitch
A project using Spring Boot and Neo4j could be a web-based application for managing and analyzing a graph-based database. Spring Boot provides the framework for creating a stand-alone Java application with a variety of useful features such as dependency injection, automatic configuration, and easy access to database resources. Neo4j is a popular graph database management system that can be used to model and query complex relationships between data. Together, these technologies can be used to build an application that allows users to create and visualize connections between different data elements, such as people, organizations, and events. This application could be used in a variety of fields such as social network, recommendation system, fraud detection and more.

# Why Spring Webflux?
There are several reasons why you might choose to use WebFlux (Project Reactor) instead of the traditional Spring MVC in your application:

1)Non-blocking: WebFlux is based on a non-blocking programming model, meaning it can handle a large number of concurrent requests without the need for thread blocking. This can result in better performance and scalability for high-concurrency applications.
2)Reactive programming: WebFlux is built on top of Project Reactor, a reactive programming library. This allows you to write code that responds to changes in data streams in a more efficient and predictable way.
3)Full asynchronous support: WebFlux supports both asynchronous and synchronous request handling. It allows you to fully leverage the benefits of asynchronous programming, such as better resource utilization and lower latency.
4)Better error handling: WebFlux has built-in support for handling errors and exceptions in a more consistent and efficient way.
5)Improved testability: WebFlux's non-blocking and reactive programming model makes it easier to test, especially when it comes to testing asynchronous code.

That being said, Spring MVC is still a good choice for many types of applications, especially those with simpler requirements or where performance is not as critical.

# Twitch Sendbox Link
As first step you have to setup your local connection to Neo4j, You can find all info's here
https://medium.com/neo4j/introducing-the-new-twitch-sandbox-bdda36a946bb

# Technologies
- SpringBoot ( Webflux ) 
- Using NoSQl db Neo4j ) 

#Please Note 
This project is only for study purposes, I will continue to push,add, modify methods when I have time and inclination =)
