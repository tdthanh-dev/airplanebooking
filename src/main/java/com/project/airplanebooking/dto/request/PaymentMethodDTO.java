package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {

    @NotBlank(message = "Method name is required")
    @JsonProperty("method_name")
    private String methodName;

    @NotBlank(message = "Description is required")
    @JsonProperty("description")
    private String description;

}