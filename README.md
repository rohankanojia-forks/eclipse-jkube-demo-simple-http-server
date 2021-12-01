# Simple HTTP Web Server in plain Java using 

This is a simple web application which hosts a static web page at root endpoint. It's not using any popular java framework and is implemented by using JDK's `com.sun.net.httpserver.HttpServer` class.

## How to Build?
```sh
gradle clean build
```

## How to Run?
```
java -jar build/libs/simple-http-server-1.0-SNAPSHOT.jar
```

## Deploying to Kubernetes
I've prepared this repository for demo for Eclipse JKube Kubernetes Gradle Plugin `k8sWatch` task to demonstrate live reload upon changes. I've used minikube as kubernetes cluster for testing. You can start this via this command:
```sh
minikube start
```

Once minikube is up and running, you need to expose minikube's docker daemon to your local machine:
```sh
# To point your shell to minikube's docker-daemon, run:
eval $(minikube -p minikube docker-env)
```

You can then run Kubernetes Gradle Plugin tasks to build and deploy application:
```sh
gradle k8sBuild k8sResource k8sApply
```

## Watching Application for changes and Automatic Updates
We'll use Kubernetes Gradle Plugin `k8sWatch` task for watching over the project in case we make any change. `k8sWatch` would automatically rebuild image and restart deployment whenever it detects a change in jar archive. 

In one terminal window, run `k8sWatch` task:
```sh
gradle k8sWatch
```

Make some changes to the project and then compile project in another terminal window:
```
gradle build
```

You'll notice `k8sWatch` has detected changes and rebuild container image and restarted Deployment.
