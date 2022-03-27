Springboot Kafka sample application
==============================

To run application locally using kafka installed in docker: <br/>

| type                       | commands                    | description            |
|----------------------------|-----------------------------|------------------------|
| docker                     | docker-compose up -d        | starts kafka in docker |
| docker                     | docker-compose down         | stops kafka            |

Building the project
====================
To build project, run the command: `mvn clean install`.


Running a Spring Boot module
====================
To run a Spring Boot application, run the command: `mvn spring-boot:run`.

Test application locally
====================
Start kafka in docker
`docker-compose up -d `

Create topic

`docker exec broker kafka-topics --bootstrap-server broker:9092 --create --topic abacusdynamics-dev`

Publish some messages

`docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic abacusdynamics-dev`

Type few messages

````
this is my first kafka message
hello world!
this is my third kafka message. I’m on a roll :-D
````

When you’ve finished, press Ctrl-D to return to your command prompt.

Check application logs to verify you got the messages.

Here is sample:

```
Message received:'ConsumerRecord(topic = abacusdynamics-dev, partition = 0, leaderEpoch = 0, offset = 0, CreateTime = 1648355868589, serialized key size = -1, serialized value size = 30, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = this is my first kafka message)'

Message received:'ConsumerRecord(topic = abacusdynamics-dev, partition = 0, leaderEpoch = 0, offset = 1, CreateTime = 1648355868603, serialized key size = -1, serialized value size = 12, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = hello world!)'

Message received:'ConsumerRecord(topic = abacusdynamics-dev, partition = 0, leaderEpoch = 0, offset = 2, CreateTime = 1648355870189, serialized key size = -1, serialized value size = 51, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = this is my third kafka message. I’m on a roll :-D)'
```

Stop springboot application.

Stop kafka

`docker-compose down`


Running Tests
=============
The command `mvn clean install` from within a module will run the unit tests in that module.
