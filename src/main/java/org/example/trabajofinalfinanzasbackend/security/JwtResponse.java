package org.example.trabajofinalfinanzasbackend.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;

    public String getToken() {
        return token;
    }

    public JwtResponse(String token) {
        super();
        this.token = token;
    }
}
