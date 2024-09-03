package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Identity document is required")
    @Digits(integer = MAX_DOCUMENT, fraction = ZERO, message = "Identity document must be numeric and cannot contain decimals")
    private Long identityDocument;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = PHONE_NUMBER, message = "Phone number must be a maximum of MAX_PHONE characters and may include the '+' symbol")
    private String phone;

    @NotNull(message = "Birthdate is required")
    private LocalDate birthdate;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must have a valid format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;


}
