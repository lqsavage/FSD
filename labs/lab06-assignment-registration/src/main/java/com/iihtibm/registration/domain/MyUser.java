package com.iihtibm.registration.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author savagelee
 */
@Data
@Entity
@Table(name = "myuser")
public class MyUser implements Serializable {
    private static final long serialVersionUID = 3497935890426858541L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(min=2, max=20)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=2, max=20)
    private String username;

    @NotBlank
    @Size(min=6, max=60)
    private String password;

    @NotBlank
    private String role;

    @Transient
    private boolean accountNonExpired = true;

    @Transient
    private boolean accountNonLocked= true;

    @Transient
    private boolean credentialsNonExpired= true;

    @Transient
    private boolean enabled= true;

}
