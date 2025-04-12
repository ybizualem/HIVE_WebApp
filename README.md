# Hive - A Campus-based Networking Solution

### Application Nature and Purpose
Hive is a campus-based networking app designed for college students. By leveraging a user's university email, Hive automatically integrates them into their campus's database, aligning studying preferences and habits to connect them with like-minded peers. As academic journeys evolve, Hive adapts, enabling users to adjust their study preferences and schedules according to the changing semesters and courses.

### Team Members
- Nnaemeka Okonkwo
- Kwaku Agyapong
- Yeabsira Bizualem

## Microservices Architecture

Hive is built on a microservices architecture, comprising of *three core services* that interact with each other and external APIs to deliver a seamless user experience.

![Hive.png](image%2FHive.png)

### 1. "Profile Management"
- **Technologies:** Java + SpringBoot, h2
- "Entrance to the app", users register with an email password and a number of profile details that are entered into the attached h2 database. From here users can access the main UI where they can create requests to be matched with other users and access chatrooms which are created upon accepting a match request.
- **Dependencies:**
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-thymeleaf
- spring-boot-starter-web
- spring-boot-devtools
- mysql-connector-java
- h2
- spring-boot-devtools
- spring-boot-starter-test
- spring-security-test
- jquery
- bootstrap
- webjars-locator-core
  
### 2. "Matcher" - OpenAI Based Matchmaking & Invitation Maker 
- **Technologies:** Java, SpringBoot, OpenAI GPT 3.5,swagger ui(for testing purpose)
- Upon recieving a match request from the Thymeleaf UI in the Profile Manager, the LLM makes a match between the user making a request and a user in the database. The match is then presented to the end user where they are prompted to accept or deny the match. This match is an LLM generated inquiry on potential study spots inthe area of the University/City and includes a generated invitation to the party of interest which is pulled from the database. Upon a denial another match is made, upon acceptance a request to create a chat between the two users is sent to the chat service.
- **Dependencies:**
- spring-boot-starter-web
- spring-ai-openai-spring-boot-starter
- lombok
- spring-boot-starter-test

### 3. User Chat Service
- **Technologies:** Pusher API, h2 database 
- After recieving confirmation from the user regarding the match, the two users information is sent to the Chat Service which uses Pusher API to open a real-time chatroom which allows for further communication between users. This link between the two users would be made after a match is made.
- **Dependencies:**
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- pusher-http-java
- springdoc-openapi-starter-webmvc-ui
- h2
- spring-boot-starter-test

## Features
### Cross-Service Communication

- **User Registration and Profile Setup**: Handled by the Mock Login & Profile Management Microservice, which stores essential user profile data.
- **Study Buddy Matching and Invitations**: Managed by the Study Buddy Matching Microservice, utilizing OpenAI for personalized recommendations and managing the invitation process.
- **Real-time Chat**: Enabled through the Chat Microservice, allowing users to communicate once they've connected for study sessions.


## Installation
### Kubernetes
Use the following commands to clone the latest version directly from our repo and cd into it to continue with your local configuration.
```bash
git clone https://github.com/nokonkwo-jpg/socialAcademic.git
cd socialAcademic
```
Once in the working directory run the following commands to apply the manifests inside the kubernetes folder
```
kubectl apply -f kubernetes/
```
Then, run this line to verify that each pod has been deployed and is running
```
kubectl get pods
```
Once the service is confirmed to be runnning, visit the ip below to start interacting with the application
```
34.36.198.127
```
