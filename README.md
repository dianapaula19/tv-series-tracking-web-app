# TV series tracker

> **Original version:** this README and some fixes were added in 2026. To see the project exactly as it was first built, browse commit [`ec8cd38`](https://github.com/dianapaula19/tv-series-tracking-web-app/tree/ec8cd385ca10a27969818a371c1b33360cb6c69b) (2021-02-18).

A social web app for tracking the TV series you watch, built in Java with Spring Boot and Vaadin (2020).

  * create and sign into an account
  
  ![register](images/register.png)
  
  ![sign_ind](images/sign_in.png)
  
  * keep track of the episodes you watched by adding or removing them from your list
  
  ![tv_series_list](images/tv_series_list.png)
  
  ![list_of_watched_episodes](images/list_of_watched_episodes.png)
  
  * see how much time you spent watching your favourite series each month
  
  ![statistics](images/statistics.png)
  
  * befriend other users
  
  ![people_you_may_know](images/people_you_may_know.png)
  
  ![friend_requests](images/friend_requests.png)
  
  * challenge your friends to binge watch their favourite series and complete their challenges
  
  ![friend_list](images/friend_list.png)
  
  ![challenges](images/challenges.png)

## Stack

Java 11, Spring Boot 2.2, Spring Data JPA, Vaadin 14, MySQL.

![Class diagram](images/diagram_java_project.png)

## Running

Needs a MySQL server with a database called `tv_series_tracking_db` (settings in
`src/main/resources/application.properties`; the host can be set with `MYSQL_HOST`).

```bash
./mvnw spring-boot:run      # then open http://localhost:8080
```
