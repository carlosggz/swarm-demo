# Swarm demo

This project shows how to deploy a spring boot application to a swarm cluster.

## Requirements

- Java 17
- Docker core
- Docker Swarm (if you have installed Docker Desktop you already have it)

## The application

The application is a rest api with a single endpoint. You can run it and access to the endpoint.

    ./gradlew bootRun

in another command line run a curl command:

    curl http://localhost:8080

The result will be something like:

    Response from 5f2a2b2f-5056-4afe-b590-ec03c6d5c832

This id is an unique identifier for the application instance running.

To stop the application press Ctrl+C

## The Swarm cluster

To start your cluster, execute:

    docker swarm init

The cluster will start. Remember to leave the cluster at the end of your tests using:

    docker swarm leave --force

## The service

### Building the image

To deploy the application into the cluster, first you must build the image. There are two ways for it:

Using the gradle plugin. For it execute:

    ./gradlew bootBuildImage --imageName=swarm/demo

Or using the standard way:

    generate the jar file: 
    
    ./gradlew clean assemble
    
    build the image using the example DockerFile: 
    
    docker build -t swarm/demo .

Now that the image was built, you can check it:

    docker images | grep "swarm/demo"  

### Deploy the application

It's time to deploy the service. For it, we can execute:

    docker service create --name swarm-demo --replicas 3 --publish published=8080,target=8080 swarm/demo:latest

This command will deploy the application exposing the port 8080 and with 3 instances. You can check it executing:

    docker service ls

To test that we have 3 instances, execute the previous curl command 3 times, and check that the unique identifier will be different in each case.

### Scalate the application

If you want to scalate the service, for example to more instances, execute:

    docker service scale swarm-demo=5

Now, repeat the same curl command 5 times and the ids will be different. You can list the services to verify that now it contains 5 instances.

## Clean up

To remove the service execute:

    docker service rm swarm-demo

List the services, and you will see the application is not present.

To end the test, leave the cluster with the command above:

    docker swarm leave --force 

