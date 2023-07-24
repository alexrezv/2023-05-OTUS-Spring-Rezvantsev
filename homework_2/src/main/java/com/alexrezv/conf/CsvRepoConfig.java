package com.alexrezv.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Data
@Component
public class CsvRepoConfig {

    @Value("${repo.csv.row-separator}")
    private char rowSeparator;

    @Value("${repo.csv.skip-first-row}")
    private boolean skipFirstRow;

    @Value("${repo.csv.filename}")
    private String fileName;

}
