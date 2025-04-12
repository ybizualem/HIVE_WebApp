package dev.matcher.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "index",
        "message",
        "logprobs",
        "finish_reason"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice {
    @JsonProperty("index")
    public Integer Index;
    @JsonProperty("message")
    public Message message;
    @JsonProperty("logprobs")
    public Object logprobs;
    @JsonProperty("finish reason")
    public String finishReason;
}
