package com.where.WhereYouAt.repository;

import com.where.WhereYouAt.domain.Friend;
import com.where.WhereYouAt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend,Long> {


    ArrayList<Friend> findByUserId(Long userId);

    Optional<Friend> findByUserIdAndUserId1(Long userId, Long friendId);
}
