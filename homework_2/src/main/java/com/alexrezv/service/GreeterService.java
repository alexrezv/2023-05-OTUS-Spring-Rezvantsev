package com.alexrezv.service;

import com.alexrezv.domain.User;

public interface GreeterService {

    /**
     * Outputs greeting configured by {@code greeter.greeting} property
     */
    void greet();

    /**
     * Asks user to input credentials, then returns {@link User}.
     *
     * @return user
     */
    User authenticateUser();

}
