package com.alexrezv.service;

import com.alexrezv.domain.User;

public interface UserService {

    String ATTR_FIRSTNAME = "firstname";

    String ATTR_LASTNAME = "lastname";

    User build(String firstName, String lastName);

}
