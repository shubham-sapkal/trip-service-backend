package com.trip_service.trip_service.security.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class DTOEntity {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenBodyDTO {
        private String username;
        private String email;
        private List<String> roles;
    }

}
