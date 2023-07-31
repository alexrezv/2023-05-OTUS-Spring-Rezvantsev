package com.alexrezv.service.impl;

import com.alexrezv.conf.GreeterConfig;
import com.alexrezv.domain.User;
import com.alexrezv.service.GreeterService;
import com.alexrezv.service.IOService;
import com.alexrezv.service.UserService;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.alexrezv.service.UserService.ATTR_FIRSTNAME;
import static com.alexrezv.service.UserService.ATTR_LASTNAME;
import static java.util.function.Function.identity;

@RequiredArgsConstructor
@Service
public class GreeterServiceImpl implements GreeterService {

    private static final String UNKNOWN = "unknown";

    private final GreeterConfig greeterConfig;

    private final IOService ioService;

    private final UserService userService;

    @Override
    public void greet() {
        ioService.printLine(greeterConfig.getGreeting());
    }

    @Override
    public User authenticateUser() {
        ioService.printLine(greeterConfig.getUserAuthPrompt());
        var creds = List.of(ATTR_FIRSTNAME, ATTR_LASTNAME)
                .toMap(identity(), ioService::askForInput);
        return userService.build(
                creds.getOrElse(ATTR_FIRSTNAME, UNKNOWN),
                creds.getOrElse(ATTR_LASTNAME, UNKNOWN));
    }

}
