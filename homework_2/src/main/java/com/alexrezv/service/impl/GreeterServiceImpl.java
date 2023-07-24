package com.alexrezv.service.impl;

import com.alexrezv.conf.GreeterConfig;
import com.alexrezv.service.GreeterService;
import com.alexrezv.service.IOService;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.function.Function.identity;

@RequiredArgsConstructor
@Service
public class GreeterServiceImpl implements GreeterService {

    private final IOService ioService;

    private final GreeterConfig greeterConfig;

    @Override
    public void greet() {
        ioService.printLine(greeterConfig.getGreeting());
    }

    @Override
    public Map<String, String> askUserCredentials(String... credentialsToAsk) {
        ioService.printLine(greeterConfig.getInputPrompt());
        return List.of(credentialsToAsk)
                .toMap(identity(), this::askForCredential);
    }

    private String askForCredential(String credential) {
        ioService.printLine(credential);
        return ioService.readLine();
    }

}
