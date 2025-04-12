package dev.matcher.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {

    @Value("${spring.ai.openai.api-key}")
    private String OpenAIKey;

    @Bean
    public RestTemplate template (){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add((((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + OpenAIKey );
            return execution.execute(request, body);
        })));
        return template;
    }
}
