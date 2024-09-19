package org1.acme.user.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org1.acme.user.Mappers.UserMapper;
import org1.acme.user.domain.UserModel;
import org1.acme.user.DTO.UserTO;


@ApplicationScoped
public class UserAdapter {

    @Inject
    UserMapper userMapper;


    public UserTO toUserTO(UserModel user) {
        return userMapper.map(user);

    }

    public UserModel toUserModel(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("UserTO cannot be null");
        }


        UserModel UserModel = userMapper.map(user);

        if (UserModel == null) {
            throw new IllegalArgumentException("UserModel could not be mapped from UserTO");
        }
        UserModel.setId(0);
        return UserModel;

    }

}
