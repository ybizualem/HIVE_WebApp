package net.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringBootRegistrationLoginApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootRegistrationLoginApplication.class);

	@Value("${chatroom_service_url}")
	private String chatRoomServiceUrl;

	//@Value("${matcher_service_url}")
	//private String matcherServiceUrl;

	@Autowired
	private UserRepository userRepository;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			fetchChatRooms(restTemplate);
			fetchChatRoomsForUser(restTemplate);
			fetchChatRoomById(restTemplate);
			//sendAllUsersToMatcher();
		};
	}

	private void fetchChatRooms(RestTemplate restTemplate) {
		List<Map<String, Object>> chatRooms = restTemplate.getForObject(
				chatRoomServiceUrl + "/api/chatrooms", List.class);
		for (Map<String, Object> chatRoom : chatRooms) {
			log.info("Chat room ID: {}, Name: {}", chatRoom.get("id"), chatRoom.get("name"));
		}
	}

	private void fetchChatRoomsForUser(RestTemplate restTemplate) {
		String username = "nokonkwo8426@gmail.com";
		List<Map<String, Object>> userChatRooms = restTemplate.getForObject(
				chatRoomServiceUrl + "/api/chatrooms/user/" + username, List.class);
		log.info("Chat rooms for user {}: {}", username, userChatRooms);
	}

	private void fetchChatRoomById(RestTemplate restTemplate) {
		Long chatRoomId = 1L;
		ResponseEntity<Map> chatRoomResponse = restTemplate.getForEntity(
				chatRoomServiceUrl + "/api/chatrooms/" + chatRoomId, Map.class);
		if (chatRoomResponse.getStatusCode().is2xxSuccessful()) {
			Map<String, Object> chatRoom = chatRoomResponse.getBody();
			log.info("Chat room with ID {}: {}", chatRoomId, chatRoom);
		} else {
			log.error("Failed to fetch chat room with ID {}", chatRoomId);
		}
	}
/**
	//@PostMapping("/send-all")
	public ResponseEntity<String> sendAllUsersToMatcher() {
		List<User> users = userRepository.findAll();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(matcherServiceUrl + "/invites", users, String.class);
		return ResponseEntity.ok("Users sent to matcher microservice.");
	}
**/
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRegistrationLoginApplication.class, args);
	}
}
