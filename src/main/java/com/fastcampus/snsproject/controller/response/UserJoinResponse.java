package com.fastcampus.snsproject.controller.response;

import com.fastcampus.snsproject.model.User;
import com.fastcampus.snsproject.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserJoinResponse {

    private Integer id;
    private String userName;
    private UserRole userRole;
    private Timestamp registeredAt;

    public static UserJoinResponse fromUser(User user) {
        return new UserJoinResponse(
                user.getId(),
                user.getUserName(),
                user.getUserRole(),
                user.getRegisteredAt()
        );
    }

}
