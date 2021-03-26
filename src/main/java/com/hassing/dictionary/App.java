package com.hassing.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    private static Dictionary dictionary;
    public static void main( String[] args )
    {
        String command;
        dictionary = new Dictionary();
        System.out.println("Welcome to the Memory Dictionary");
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("> ");
            command = scanner.nextLine();
            parseCommand(command);
            System.out.println(command);
        } while (!command.equals("EXIT"));
    }

    public static void parseCommand(String command) {
        String[] split = command.split(" ");
        if (split.length > 3) {
            printHelp();
            return;
        }
        switch(split[0]) {
            case("KEYS"):
            case("MEMBERS"):
            case("ADD"):
            case("REMOVE"):
            case("REMOVEALL"):
            case("CLEAR"):
            case("KEYEXISTS"):
            case("VALUEEXISTS"):
            case("ALLMEMBERS"):
            case("ITEMS"):
            default:
                printHelp();
                break;
        }
    }

    public static void printHelp() {
        System.out.println("Dictionary Use");
        System.out.println("KEYS: Returns all keys in dictionary. Order not guaranteed");
        System.out.println("MEMBERS: Returns the collection of strings for a given key");
        System.out.println("ADD: add a member to a collection for a given key");
        System.out.println("REMOVE: Removes a value from a key");
        System.out.println("REMOVEALL: Removes all values for a key and removes key from dictionary");
        System.out.println("CLEAR: Removes all keys and all values from the dictionary");
        System.out.println("KEYEXISTS: Returns whether a key exists or not");
        System.out.println("VALUEEXISTS: Returns whether a value exists within a key");
        System.out.println("ALLMEMBERS: Returns all values in the dictionary");
        System.out.println("ITEMS: Returns all keys in the dictionary and all of their values");
    }
}
