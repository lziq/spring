This repo shows a simplified microservice architecture

![Screen Shot 2023-04-14 at 4.08.28 PM.png](Screen%20Shot%202023-04-14%20at%204.08.28%20PM.png)

API Gateway: an intermediate entry between external requests and internal system. Mainly used for authentication, authorization, rate limiting, service discovery, protocol conversion, monitoring, etc.

Eureka: the master node in distributed system. Stores information about worker nodes and receive heartbeats from worker nodes. 

Feign: a proxy to send requests with client side load balancing. 

Circuit break: detects failed microservices and return default response 
