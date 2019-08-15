package com.iihtibm.registration.service;

import com.iihtibm.registration.models.User;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface UserService {

    int create(User user);

    int delete(Long id) throws ObjectNotFoundException;

    List<User> findAll();

    User findById(Long id);

    User findById(String id);

    User findByUserId(String id);

    User findByEmail(String email);

}
