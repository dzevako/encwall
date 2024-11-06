package ru.dz;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello! This App is for vertical enc and dec strings. You can enter 'quit' or 'q' for exit.");
        Scanner scanner = new Scanner(System.in);
        String string = getParam("Enter your string for processing:", scanner);
        String table = getParam("Enter table:", scanner);
        String key = getParam("Enter key:", scanner);
        String direction = getParam("Enter direction: 'back'('b'), 'front'('f')", scanner);

        String result = vshift(string, table, key, direction);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Result: '" + result + "'");
    }

    private static String vshift(String string, String table, String key, String direction)
    {
        int strLen = string.length();
        int tableLen = table.length();

        String fullKey = "";
        while (fullKey.length() < strLen) fullKey += key;

        boolean direct = !direction.equals("b") && !direction.equals("back");

        StringBuilder result = new StringBuilder();

        for (int index = 0; index < string.length(); index++)
        {
            char newSymbol;
            char symbol = string.charAt(index);
            if (symbol == '-')
            {
                newSymbol = symbol;
            }
            else
            {
                int symbolIndex = table.indexOf(symbol);
                if (symbolIndex < 0)
                    throw new RuntimeException("Bad table.");
                char keySymbol = fullKey.charAt(index);
                int keyIndex = table.indexOf(keySymbol);
                if (keyIndex < 0)
                    throw new RuntimeException("Bad table.");
                int newIndex = symbolIndex + (direct ? keyIndex : - keyIndex);
                newIndex = newIndex > tableLen - 1 ? newIndex - tableLen : newIndex;
                newIndex = newIndex < 0 ? newIndex + tableLen : newIndex;
                newSymbol = table.charAt(newIndex);
            }
            result.append(newSymbol);
        }
        return result.toString();
    }

    private static void checkQuit(String string)
    {
        if (string.equals("q") || string.equals("quit"))
        {
            System.out.println("Quit...");
            System.exit(0);
        }
    }

    private static String getParam(String title, Scanner scanner)
    {
        String string = "";
        while (string.isEmpty())
        {
            System.out.println(title);
            string = scanner.next();
            checkQuit(string);
        }
        return string;
    }
}