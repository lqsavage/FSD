package com.iihtibm.registration.security.browser;

import com.iihtibm.registration.domain.MyUser;
import com.iihtibm.registration.repository.MyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author savagelee
 */
@Configuration
public class UserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserRepository myUserRepository;

    private enum ROLETYPE {
        ROLE_ADMIN("admin"),
        ROLE_USER("user");

        private String typeName;

        ROLETYPE(String typeName) {
            this.typeName = typeName;
        }

        /**
         * Get roletype via typename
         *
         * @param typeName
         */
        public static ROLETYPE fromTypeName(String typeName) {
            for (ROLETYPE type : ROLETYPE.values()) {
                if (type.getTypeName().equals(typeName)) {
                    return type;
                }
            }
            return null;
        }

        public String getTypeName() {
            return this.typeName;
        }
    }

    public List<MyUser> findAllUsers() {
        return myUserRepository.findAll();
    }

    public MyUser findByUsername(String username) {
        return myUserRepository.findByUsername(username);
    }

    public MyUser saveUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }

    public void updateUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        myUserRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = this.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist.");
        }

        String password = user.getPassword();
        String strRole = ROLETYPE.fromTypeName(user.getRole()).name();
        String passwordAfterEncoder = passwordEncoder.encode(password);
        logger.info("Input password is {}", password);
        logger.info("PasswordAfterEncoder is {}", passwordAfterEncoder);

        SimpleGrantedAuthority roleUser = new SimpleGrantedAuthority(strRole);
        Collection<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(roleUser);

        return new org.springframework.security.core.userdetails.User(username, password, user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), grantedAuths);
    }
}
