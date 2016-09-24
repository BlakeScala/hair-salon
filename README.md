# _Hair_Salon_

#### By _Blake Scala_

## Description

_Application to manage a hair salon with stylists and clients._

## Setup/Installation Requirements

*_Clone this directory_
* _Setup database by opening psql and running these commands:_
    * CREATE DATABASE hair_salon;
    * psql hair_salon < media.sql;
    * CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
* Type 'gradle run' in the project directory terminal window.
* Open your browser and go to localhost:4567

## Specs
* Application lists all stylists
* Application returns all stylist details
* Application stores all stylists in database
* Application can update stylist information.
* Application can delete any stylist.
* Application stores client data in database.
* Application can select stylist/client by id.
* Application assigns a stylistId to every client.

## Technologies Used
Java, JUnit, Spark, PostgreSQL, Gradle

## GitHub link

https://github.com/BlakeScala/hair_salon

## Licensing

* MIT

Copyright (c) 2016 **_Blake Scala_**
