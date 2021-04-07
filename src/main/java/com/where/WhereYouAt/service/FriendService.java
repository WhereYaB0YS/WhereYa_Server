package com.where.WhereYouAt.service;

import com.where.WhereYouAt.controller.dto.friend.FriendsResponseDto;
import com.where.WhereYouAt.domain.Friend;
import com.where.WhereYouAt.domain.User;
import com.where.WhereYouAt.exception.AlreadyExistedFriendException;
import com.where.WhereYouAt.exception.NotExistedFriendException;
import com.where.WhereYouAt.exception.NotExistedUserIdException;
import com.where.WhereYouAt.repository.FriendRepository;
import com.where.WhereYouAt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FriendService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;


    //친구추가
    public void addFriend(Long userId, String friendNickname) {
        User friend = userRepository.findByNickname(friendNickname)
                .orElseThrow(NotExistedUserIdException::new);

        if(friend.getId()==userId){
            throw new RuntimeException();
        }

        Optional<Friend> friendRel = friendRepository.findByUserIdAndUserId1(userId,friend.getId());

        if(friendRel.isPresent()){
            throw  new AlreadyExistedFriendException();
        }

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        Friend friendRelation = Friend.builder()
                .user(user)
                .userId1(friend.getId())
                .build();

        friendRepository.save(friendRelation);

        user.getFriends().add(friendRelation);
    }

    //친구목록 보기
    public ArrayList<FriendsResponseDto> getFriends(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);


        ArrayList<FriendsResponseDto> friends = new ArrayList<>();

        ArrayList<Friend> friendRelations = friendRepository.findByUserId(userId);

        for(Friend friend : friendRelations){
            User friend1 = userRepository.findById(friend.getUserId1())
                    .orElseThrow(NotExistedUserIdException::new);
            FriendsResponseDto dto = FriendsResponseDto.builder()
                    .nickname(friend1.getNickname())
                    .profileImg(friend1.getProfileImg())
                    .star(friend.isStar())
                    .build();
            friends.add(dto);
        }
        Collections.sort(friends);
        return friends ;
    }

    //친구삭제
    public void deleteFriend(Long userId, String friendNickname) {

        User friend = userRepository.findByNickname(friendNickname)
                .orElseThrow(NotExistedUserIdException::new);

        Friend friendRel = friendRepository.findByUserIdAndUserId1(userId,friend.getId())
                .orElseThrow(NotExistedFriendException::new);

        friendRepository.delete(friendRel);

    }

    //즐겨찾기
    public String bookmark(Long userId, String friendNickname) {

        User friend = userRepository.findByNickname(friendNickname)
                .orElseThrow(NotExistedUserIdException::new);

        Friend friendRel = friendRepository.findByUserIdAndUserId1(userId,friend.getId())
                .orElseThrow(NotExistedFriendException::new);

        if(friendRel.isStar()==true){
            friendRel.setStar(false);
            return "해당 친구가 즐겨찾기목록에 추가되었습니디";
        }else{
            friendRel.setStar(true);
            return "해당 친구가 즐겨찾기목록에서 제외되었습니다";
        }
    }



}
