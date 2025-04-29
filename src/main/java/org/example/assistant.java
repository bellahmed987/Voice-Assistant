package org.example;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.time.LocalTime;

class Assistant {

    public void greet() {
        System.out.println("Hello, I am your assistant. How can I help you today?");
    }

    public void sayGoodbye() {
        System.out.println("Goodbye! Have a great day.");
    }

    public void processCommand(String command) {
        command = command.toLowerCase();

        if (command.contains("help") || command.contains("commands")) {
            showHelp();
        } else if (command.contains("time")) {
            System.out.println("The current time is: " + getCurrentTime());
        } else if (command.contains("open notepad")) {
            openApp("notepad.exe");
        } else if (command.contains("open calculator")) {
            openApp("calc.exe");
        } else if (command.contains("open youtube")) {
            openWebsite("https://www.youtube.com");
        } else if (command.contains("open google")) {
            openWebsite("https://www.google.com");
        } else if (command.contains("open facebook")) {
            openWebsite("https://www.facebook.com");
        } else if (command.contains("open github")) {
            openWebsite("https://www.github.com");
        } else if (command.contains("joke")) {
            tellJoke();
        } else if (command.contains("calculate")) {
            handleCalculation(command);
        } else {
            System.out.println("Sorry, I didn't understand that command. Type 'help' to see available commands.");
        }
    }

    private void showHelp() {
        System.out.println("\nHere are the available commands:");
        System.out.println("- help / commands            → Show this help message");
        System.out.println("- time                       → Show the current time");
        System.out.println("- open notepad               → Open Notepad application");
        System.out.println("- open calculator            → Open Calculator application");
        System.out.println("- open youtube               → Open YouTube in browser");
        System.out.println("- open google                → Open Google in browser");
        System.out.println("- open facebook              → Open Facebook in browser");
        System.out.println("- open github                → Open GitHub in browser");
        System.out.println("- joke                       → Hear a joke");
        System.out.println("- calculate [num1] [op] [num2]  → Perform a calculation (e.g., calculate 5 + 3)");
        System.out.println("- exit                       → Exit the assistant\n");
    }

    private String getCurrentTime() {
        return LocalTime.now().toString();
    }

    private void openApp(String appName) {
        try {
            Runtime.getRuntime().exec(appName);
            System.out.println("Opening " + appName + "...");
        } catch (IOException e) {
            System.out.println("Failed to open " + appName + ": " + e.getMessage());
        }
    }

    private void openWebsite(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI.create(url));
                System.out.println("Opening " + url);
            } else {
                System.out.println("Desktop operations not supported on this system.");
            }
        } catch (IOException e) {
            System.out.println("Failed to open website: " + e.getMessage());
        }
    }

    private void tellJoke() {
        System.out.println("Why don't scientists trust atoms? Because they make up everything!");
    }

    private void handleCalculation(String command) {
        String[] parts = command.split(" ");
        if (parts.length >= 4) {
            try {
                double num1 = Double.parseDouble(parts[1]);
                String operator = parts[2];
                double num2 = Double.parseDouble(parts[3]);
                double result = calculate(num1, operator, num2);
                System.out.println("The result is: " + result);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for calculation.");
            }
        } else {
            System.out.println("Calculation format: calculate 5 + 3");
        }
    }

    private double calculate(double num1, String operator, double num2) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> (num2 != 0) ? num1 / num2 : Double.NaN;
            default -> {
                System.out.println("Unknown operator.");
                yield 0;
            }
        };
    }
}
