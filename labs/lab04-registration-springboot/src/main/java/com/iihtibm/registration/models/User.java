package com.iihtibm.registration.models;

import lombok.Data;

/**
 * @author savagelee
 */
@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
}
