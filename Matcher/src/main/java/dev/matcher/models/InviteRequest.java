package dev.matcher.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "model",
        "messages",
        "temperature",
        "max_tokens",
        "top_p",
        "frequency_penalty",
        "presence_penalty"
})

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteRequest {

    @JsonProperty("model")
    public String model;
    @JsonProperty("messages")
    public List<Message> messages;
    @JsonProperty("temperature")
    public Integer temperature;
    @JsonProperty("max_tokens")
    public Integer maxTokens;
    @JsonProperty("top_p")
    public Integer topP;
    @JsonProperty("frequency_penalty")
    public Integer frequencyPenalty;
    @JsonProperty("presence_penalty")
    public Integer presencePenalty;

}
