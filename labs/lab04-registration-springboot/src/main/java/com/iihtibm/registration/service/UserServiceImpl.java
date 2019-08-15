package com.iihtibm.registration.service;

import com.iihtibm.registration.models.User;
import com.iihtibm.registration.repository.UserJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Long.parseLong;

/**
 * @author savagelee
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserJdbcRepository userJdbcRepository;

    @Override
    public int create(User user) {
        return userJdbcRepository.insert(user);
    }

    @Override
    public int delete(Long id) {
        return userJdbcRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userJdbcRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userJdbcRepository.findById(id);
    }

    @Override
    public User findById(String id) {
        return userJdbcRepository.findById(parseLong(id));
    }

    @Override
    public User findByUserId(String id) {
        return userJdbcRepository.findById(parseLong(id));
    }

    @Override
    public User findByEmail(String email) {
        return userJdbcRepository.findByEmail(email);
    }
}
