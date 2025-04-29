package org.example;

import java.util.Scanner;
import java.time.LocalTime;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Assistant assistant = new Assistant();

        // Greet the user
        assistant.greet();

        // Command loop
        while (true) {
            System.out.print("Your Command: ");
            String command = scanner.nextLine().toLowerCase();

            if (command.equals("exit")) {
                assistant.sayGoodbye();
                break;
            }

            assistant.processCommand(command);
        }

        scanner.close();
    }
}