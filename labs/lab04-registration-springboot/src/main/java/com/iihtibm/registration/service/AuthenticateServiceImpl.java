package com.iihtibm.registration.service;

import com.iihtibm.registration.constants.Constant;
import com.iihtibm.registration.models.User;
import com.iihtibm.registration.repository.UserJdbcRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author savagelee
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    UserJdbcRepository userJdbcRepository;

    @Override
    public boolean IsValidUser(User user) {
        User resultUser = userJdbcRepository.findByEmail(user.getEmail());
        if (resultUser != null && BCrypt.checkpw(user.getPassword(), resultUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getJWT(User user) {
        String token = Jwts.builder().setSubject("Lab06Assignment").setId(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + 6000000L)).signWith(SignatureAlgorithm.HS512, Constant.KEY)
                .compact();
        return token;
    }

    @Override
    public boolean IsValidRequest(String token) {
        System.out.println(Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody().get("jti"));
        return Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody().getSubject()
                .equals("Lab06Assignment");
    }

    public String getUser(HttpServletRequest request) {
        return null;

    }

    @Override
    public boolean registerUser(User user) {

        String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        user.setRole("USER");
        int retUser = userJdbcRepository.insert(user);

        if (retUser > 0) {
            return true;
        } else {
            return false;
        }
    }
}
