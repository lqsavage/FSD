package com.iihtibm.registration.domain.dto;

import lombok.Data;

/**
 * @author savagelee
 */
@Data
public class MyUserDTO {
    private String reg_fullname;
    private String reg_email;
    private String reg_username;
    private String reg_password;
    private String reg_role;
}
