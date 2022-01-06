# event-sourcing-application

This application is an implementation of the event sourcing design pattern. The fundamental idea of this pattern is storage all changes of an object in the application. This behavior allows you to create a timeline of the object and track any kind of adversity during the steps of execution.

In this application, the _EventEntity_ class has attributes to identify the event and information such as _name_, _payload_, _creation date_ and _stacktrace_ when it fails.


### Event Class:

| _collection_ | _type_        |
|--------------|---------------|
| id           | String        |
| name         | String        |
| payload      | Object        |
| error        | boolean       |
| exception    | String        |
| stacktrace   | String        |
| createdAt    | LocalDateTime |


### Application events:

**1. FetchUserDetailsEvent**: This event fetches the user information from the [myFakeApiUser](https://myfakeapi.com/api/users), the _userId_ is received by the _EventListener_ that is the trigger of the event. _EventListener_ is listening the sqs queue _event_created_queue_.

**2. FetchCarDetailsEvent**: This event fetches the car information from the [myFakeApiCar](https://myfakeapi.com/api/cars).

**3. RandomStatusCodeEvent**: This event make a request in the [RandomStatusCodeApi](https://random-status-code.herokuapp.com). The API response is a random status code, so in this event we can see the application reprocessing the event until it receives the status _200_. Therefore, many RandomStatusCode event with error are storage and only one without error.

**4. NotifyTopicEvent**: This event notifies the sns topic with the data fetched in the APIs. The topic name is _event_finished_topic._

### Application steps:

| Step    | 1. EventListener                 | 2. FetchUserDetailsEvent                    | 3. FetchCarDetailsEvent                               | 4. RandomStatusCodeEvent                        | 5. NotifyTopicEvent |
|---------|----------------------------------|---------------------------------------------|-------------------------------------------------------|-------------------------------------------------|---------------------|
| Payload | externalUserId<br/>externalCarId | externalUserId<br/>externalCarId<br/>userId | externalUserId<br/>externalCarId<br/>userId<br/>carId | userDetailsDto<br/>carDetailsDto<br/>statusCode | message             |


### Requirements:

- [Java 17](https://jdk.java.net/17/)
- [Maven 3.8.1](https://maven.apache.org/docs/3.8.1/release-notes.html)
- [LocalStack AWS emulator](https://github.com/localstack/localstack)
- [MongoDB](https://docs.mongodb.com/manual/)

### Install maven dependencies: 
```
mvn install
```

### Create localstack and mongoDB containers:
``` 
make docker-start
``` 

### Run application:
``` 
spring-boot:run
``` 

### Send message to sns topic:
``` 
make sns-new-event USER_ID=1 CAR_ID=1
``` 
_USER_ID and CAR_ID range 1~999_

### Stop containers:
``` 
docker-stop
``` 
