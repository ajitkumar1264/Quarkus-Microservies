package org1.acme.user.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org1.acme.user.domain.UserModel;
import org1.acme.user.adapter.UserAdapter;
import org1.acme.user.exception.UserException;
import org1.acme.user.DTO.UserTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class UserService {

    private List<UserModel> users=new ArrayList<>();
    private AtomicLong idGenerator=new AtomicLong(1);

    @Inject
    UserAdapter userAdapter;


    public UserTO addUser(UserTO user)
    {

        if(user==null)
        {
            throw new IllegalArgumentException("UserTO cannot be null in userservice");
        }
         UserModel NewUser;
        NewUser = userAdapter.toUserModel(user);


        Optional<UserModel> existinguser=users.stream().filter(x->x.getEmailId().equals(user.getEmailId())).findFirst();

        if(existinguser.isPresent())
        {
              throw  new UserException("user is already Exist");
        }

        NewUser.setId(idGenerator.getAndIncrement());
        users.add(NewUser);
        return userAdapter.toUserTO(NewUser);
    }

    public List<UserTO> getUsers()
    {
           List<UserTO> usersDTO=new ArrayList<>();
           for(UserModel user:users)
           {
               usersDTO.add(userAdapter.toUserTO(user));
           }

           return usersDTO;
    }

    public void deleteUser(long Id)
    {
        Optional<UserModel> UserToDelete=users.stream().filter(x->x.getId()==Id).findFirst();

        if(!UserToDelete.isPresent())
        {
            throw new UserException("Sorry but User not Found!");
        }

        users.remove(UserToDelete.get());

    }

    public UserTO updateUser(long Id,UserTO user)
    {
        Optional<UserModel> existingUser=users.stream().filter(x->x.getId()==Id).findFirst();

        if(!existingUser.isPresent())
        {
            throw new UserException("sorry but user Not found!");
        }

        UserModel UserToUpdate=existingUser.get();
        UserToUpdate.setEmailId(user.getEmailId());
        UserToUpdate.setName(user.getName());
        UserToUpdate.setMobileNo(user.getMobileNo());

        return userAdapter.toUserTO(UserToUpdate);
    }


}
