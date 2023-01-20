# Web-Customer-Tracker

A web app for tracking customers' info using Spring MVC & Hibernate

## Overview

The design structure is as follows:

<img src="https://github.com/Tianhao-Li/Web-Customer-Tracker/blob/main/demos/Structure.png">



This web application was build based on Spring MVC, which leveraged features of the Core Spring Framework. Hibernate was used for the application to communicate with backend MySQL DB, which provided the ORM.

Here a Service Layer was added between the Controller and the DAO, which meets the Service Facade design pattern, and could provide an intermediate layer for custom business logic and integrate data from multiple sources.

## Functionality 

This web application supports the following functionalities:

- List all customers

<img src="https://github.com/Tianhao-Li/Web-Customer-Tracker/blob/main/demos/Main%20menu.png">

- Add a new customer

<img src="https://github.com/Tianhao-Li/Web-Customer-Tracker/blob/main/demos/Add%20customer.png">

- Update an existing customer

<img src="https://github.com/Tianhao-Li/Web-Customer-Tracker/blob/main/demos/Update%20customer.png">

- Delete an existing customer

<img src="https://github.com/Tianhao-Li/Web-Customer-Tracker/blob/main/demos/Delete%20customer.png">

- Search for customers with certain name

<img src="https://github.com/Tianhao-Li/Web-Customer-Tracker/blob/main/demos/Search%20customer.png">

- Sort customers by certain column

<img src="https://github.com/Tianhao-Li/Web-Customer-Tracker/blob/main/demos/Sort%20by%20first%20name.png">
