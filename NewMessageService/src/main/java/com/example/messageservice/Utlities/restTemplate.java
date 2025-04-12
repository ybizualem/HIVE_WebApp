package com.example.messageservice.Utlities;

public class restTemplate {
}

//Step 3: API Call Between LLM Microservice and Message Microservice
//Prerequisites:
//
//Both microservices are containerized and deployed in a Kubernetes cluster.
//They can communicate over a cluster network or using exposed services (e.g., NodePort, ClusterIP, LoadBalancer).
//Steps to Make the API Call:
//
//Expose Your Message Microservice Endpoint:
//Ensure that your message microservice has an exposed endpoint that can be reached by the LLM microservice. If they are in the same cluster, a ClusterIP service would suffice.
//Create API Client in LLM Microservice:
//You will need to create a client in the LLM microservice that can make HTTP POST requests to your message service.
//You can use libraries like RestTemplate in Spring Boot or WebClient for making these requests.
//
//
//
// @Service
//public class MatchNotificationService {
//    private final RestTemplate restTemplate;
//    private final String messageServiceUrl = "http://message-service:8080/api/chatrooms/create-for-match";
//
//    public MatchNotificationService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }
//
//    public void notifyMatch(String user1, String user2) {
//        MatchedUsersRequest request = new MatchedUsersRequest();
//        request.setUser1(user1);
//        request.setUser2(user2);
//        ResponseEntity<String> response = restTemplate.postForEntity(messageServiceUrl, request, String.class);
//        // Handle response
//    }
//}
//Configure RestTemplate Bean:
//Ensure that you configure RestTemplate as a bean in your application context so it can be autowired into your services.
