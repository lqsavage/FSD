package com.iihtibm.registration.repository;

import com.iihtibm.registration.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author savagelee
 */
@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    public MyUser findByUsername(String username);
}
