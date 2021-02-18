package com.omnirio.userservice.service;

import com.omnirio.userservice.model.Role;
import com.omnirio.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private List<User> userList;
    private static Role customerRole;
    private static Role branchManagerRole;

    static {
        customerRole = new Role("1", "Customer", "ROLE_CUSTOMER");
        branchManagerRole = new Role("2", "Branch Manager", "ROLE_BRANCH_MANAGER");
    }

    public List<User> getUserList() {
        return userList;
    }

    public User saveUserDetails(User user) {
        Role role;
        if (user.getRoleCode().equals(customerRole.getRoleCode())) {
            role = customerRole;
        } else {
            role = branchManagerRole;
        }
        user.setRole(role);
        userList.add(user);
        return user;
    }

    public User getUserDetails(String userId) {
        User fetchedUser = null;
        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                fetchedUser = user;
            }
        }
        return fetchedUser;
    }
}
