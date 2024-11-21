package com.craftinginterpreters.lox;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    static boolean hadError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length  == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    /**
     * Run a .lox file.
     * @param path path to your .lox file.
     * @throws IOException
     */
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if (hadError) {
            System.exit(65);
        }
    }

    /**
     * REPL for Lox.
     * @throws IOException
     */
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        // for(;;) is an infinite loop.
        // This is apparently common in other languages
        // but not common for me!
        for(;;) {
            System.out.print("> ");
            String line = reader.readLine();

            // Control+D will interupt readLine
            // causing it to emit null.
            if (line == null) {
                // Thus we break on null to exit the loop.
                break;
            }
            run(line);

            // Reset error to allow user
            // to continue.
            hadError = false;
        }
    }

    // private static void run(String source) {
    //     Scanner scanner = new Scanner(source);
    //     List<Token> tokens = scanner.scanTokens();

    //     for (Token token : tokens) {
    //         System.out.println(token);
    //     }
    // }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}