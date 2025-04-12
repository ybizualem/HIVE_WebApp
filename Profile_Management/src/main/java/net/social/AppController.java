package net.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${chatroom_service_url}")
	private String chatRoomServiceUrl;

	@Value("${message_service_url}")
	private String messageServiceUrl;

	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/home")
	public String home() {
		return "logged_in";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(@ModelAttribute("user") User user,
								  @RequestParam("monday") String monday,
								  @RequestParam("tuesday") String tuesday,
								  @RequestParam("wednesday") String wednesday,
								  @RequestParam("thursday") String thursday,
								  @RequestParam("friday") String friday) {
		String hours = String.format("Monday: %s, Tuesday: %s, Wednesday: %s, Thursday: %s, Friday: %s",
				monday, tuesday, wednesday, thursday, friday);
		user.setHours(hours);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "register_success";
	}

	@GetMapping("/users")
	public String getUserByEmail(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); // Assuming the email is stored as the username

		User user = userRepo.findByEmail(email);
		if (user != null) {
			List<User> listUsers = new ArrayList<>();
			listUsers.add(user);
			model.addAttribute("listUsers", listUsers);
			return "users";
		} else {
			return "error";
		}
	}

	@GetMapping("/chatrooms")
	public String viewChatRooms(Model model) {
		try {
			ResponseEntity<List> response = restTemplate.exchange(
					chatRoomServiceUrl + "/api/chatrooms",
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List>() {}
			);
			if (response.getStatusCode() == HttpStatus.OK) {
				model.addAttribute("chatRooms", response.getBody());
			} else {
				model.addAttribute("error", "Failed to fetch chat rooms: " + response.getStatusCode());
			}
		} catch (RestClientException e) {
			model.addAttribute("error", "API call failed: " + e.getMessage());
		}
		return "chatrooms";
	}

	@GetMapping("/messages/{chatRoomId}")
	public String viewMessages(Model model, @PathVariable Long chatRoomId) {
		try {
			ResponseEntity<List> responseEntity = restTemplate.exchange(
					messageServiceUrl + "/api/messages/" + chatRoomId,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List>() {}
			);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				List<?> messages = responseEntity.getBody();
				model.addAttribute("messages", messages);
				model.addAttribute("chatRoomId", chatRoomId);
			} else {
				model.addAttribute("error", "Failed to fetch messages: " + responseEntity.getStatusCode());
			}
		} catch (Exception e) {
			model.addAttribute("error", "Failed to load messages due to: " + e.getMessage());
		}
		return "messages";
	}

	@PostMapping("/send")
	public String sendMessage(@RequestParam Long chatRoomId, @RequestParam String content, @RequestParam String receiver) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String sender = authentication.getName(); // Assuming the email is stored as the username

		Map<String, Object> messageData = new HashMap<>();
		messageData.put("sender", sender);
		messageData.put("receiver", receiver);
		messageData.put("content", content);
		messageData.put("chatRoomId", chatRoomId);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(messageData, headers);

		restTemplate.postForEntity(messageServiceUrl + "/api/messages/send", request, String.class);
		return "redirect:/messages/" + chatRoomId;
	}

	@RequestMapping("/health")
	public String getHealth() {
		return "socialAcademic";
	}
}



/**@Autowired
private UserRepository userRepository;

@Value("${matcher_service_url}")
private String matcherServiceUrl;

@PostMapping("/invites")
public String getAllUsers(Model model) {
    Iterable<User> users = userRepository.findAll();
    model.addAttribute("users", users);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(matcherServiceUrl + "/invites", users, String.class);

    if (responseEntity.getStatusCode().is2xxSuccessful()) {
        String response = responseEntity.getBody();
        model.addAttribute("matcherResponse", response);
    } else {
        model.addAttribute("matcherError", "Failed to get response from matcher microservice");
    }

    return "allusers";
}**/

