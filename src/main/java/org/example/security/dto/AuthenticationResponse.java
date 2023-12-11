package org.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response to Registration and Creating. Contents of user info + token without security
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String token;
}
