package com.alexrezv.conf;

import com.alexrezv.service.IOService;
import com.alexrezv.service.impl.IOStreamsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
public class AppConfig {

    @Bean
    public IOService ioService(
            @Value("#{ T(java.lang.System).out}") PrintStream output,
            @Value("#{ T(java.lang.System).in}") InputStream input) {
        return new IOStreamsService(output, input);
    }

}
