package it.redgabri;

import jdk.nashorn.internal.ir.Terminal;

import java.awt.*;

public class Main {
    private static TerminalFrame terminal;
    public static void main(String[] args) throws InterruptedException {
        terminal = new TerminalFrame();
        terminal.show();
        terminal.appendToTerminal("Welcome to the java terminal interface", Color.WHITE);
    }
}