package dev.matcher.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "prompt tokens",
        "completion tokens",
        "total_tokens"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usage {
    @JsonProperty("prompt tokens")
    public Integer promptTokens;
    @JsonProperty("completion tokens")
    public Integer completionTokens;
    @JsonProperty("total_tokens")
    public Integer totalTokens;
}
