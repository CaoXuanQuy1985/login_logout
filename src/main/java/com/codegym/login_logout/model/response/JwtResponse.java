package com.codegym.login_logout.model.response;

import com.codegym.login_logout.model.User;
import lombok.Data;

@Data
public class JwtResponse {
    private String token;

    private String type = "Bearer";

    private User user;
}
