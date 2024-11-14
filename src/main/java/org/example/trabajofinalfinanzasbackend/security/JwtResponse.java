package org.example.trabajofinalfinanzasbackend.security;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;

    public JwtResponse(String token) {
        super();
        this.token = token;
    }
}
