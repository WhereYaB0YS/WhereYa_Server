package com.where.WhereYouAt.repository;

import com.where.WhereYouAt.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUserId(String userId);


    Optional<User> findByNickname(String nickname);


}
