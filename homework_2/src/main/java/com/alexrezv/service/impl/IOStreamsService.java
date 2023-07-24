package com.alexrezv.service.impl;

import com.alexrezv.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOStreamsService implements IOService {

    private final PrintStream output;

    private final Scanner scanner;

    public IOStreamsService(PrintStream output, InputStream input) {
        this.output = output;
        this.scanner = new Scanner(input);
    }

    @Override
    public void printLine(String string) {
        output.println(string);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

}
