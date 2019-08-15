package com.iihtibm.registration.service;

import com.iihtibm.registration.models.User;
import org.springframework.stereotype.Service;


@Service
public interface AuthenticateService {

    boolean IsValidUser(User user);

    String getJWT(User user);

    boolean IsValidRequest(String token);

    boolean registerUser(User user);

}
