package com.alexrezv.service.impl;

import com.alexrezv.service.IOService;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;

@RequiredArgsConstructor
public class IOStreamsService implements IOService {

    private final PrintStream output;

    @Override
    public void printLine(String string) {
        output.println(string);
    }

}
