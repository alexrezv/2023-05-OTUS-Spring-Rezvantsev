package com.alexrezv.service;

import io.vavr.collection.Map;

public interface GreeterService {

    /**
     * Outputs greeting configured by {@code greeter.greeting} property
     */
    void greet();

    /**
     * Asks user to input credentials, then returns them in a map.
     *
     * @param credentialsToAsk - things to ask about user (i.e. name, age)
     * @return a {@link Map}, where keys - credentials, values - user input
     */
    Map<String, String> askUserCredentials(String... credentialsToAsk);

}
