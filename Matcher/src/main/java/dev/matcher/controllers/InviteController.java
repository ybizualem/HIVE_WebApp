package dev.matcher.controllers;


import dev.matcher.models.InviteRequest;
import dev.matcher.models.InviteResponse;
import dev.matcher.models.Message;
import dev.matcher.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// try this link localhost:8080/invites/write a short invitation to my friend dan to study at 7pm

@RestController
public class InviteController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    @Autowired
    private RestTemplate template;

    @Value("${chatgpt.api.url}")
    private String chatGptUrl;

    @Value("${chatgpt.model}")
    private String chatGptModel;

    User recipient;
    User currentUser;

    public InviteController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/findmatch")
    private User findMatch(User currentUser, List<User> userList) {
        for (User user : userList) {
            if (!user.getId().equals(currentUser.getId())) {
                if (user.getCourses() != null && user.getCourses().equals(currentUser.getCourses())) {
                    recipient = user;
                    break;
                }
                if (user.getHours() != null && user.getHours().equals(currentUser.getHours())) {
                    recipient = user;
                    break;
                }
            }
        }
        return recipient;
    }


    @Value("${other_microservice_base_url}") // Provide the base URL of the other microservice
    private String otherMicroserviceBaseUrl;

    @GetMapping("/users")
    public List<User> getAllUsersFromOtherMicroservice() {
        // Make an HTTP GET request to the other microservice to fetch all users
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> response = restTemplate.exchange(
                otherMicroserviceBaseUrl + "/allusers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {});

        // Extract and return the list of users from the response entity
        return response.getBody();
    }

    @GetMapping("/invites")
    public InviteResponse getChatGptResponse(User currentUser) {
        String prompt = getPrompt(currentUser);
        InviteRequest request = new InviteRequest();
        List<Message> messageList = new ArrayList<>();
        Message chatGptMessage = new Message();
        chatGptMessage.setRole("user");
        chatGptMessage.setContent(prompt);
        messageList.add(chatGptMessage);
        request.setModel(chatGptModel);
        request.setMessages(messageList);

        InviteResponse inviteResponse = template.postForObject(chatGptUrl, request, InviteResponse.class);
        return inviteResponse;
    }

    private String getPrompt(User user) {
        String userName = user.getName();
        String Courses = user.getCourses();
        String Hours = user.getHours();
        String University = user.getUniversity();
        String Location = user.getLocation();

        String prompt = "Find at least 5 popular study spots around " + University + ", " + Location + ". List these. Write an invitation asking my friend " + recipient.getName() + " to study " + Courses + " at one of these study spots at the following times " + Hours + " and ends with 'Best, " + userName + "'." + "Only include what I have requested in the output";
        return prompt;
    }


}
