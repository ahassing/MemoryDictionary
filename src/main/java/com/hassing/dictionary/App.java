package com.hassing.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hassing.dictionary.exceptions.AlreadyAddedException;
import com.hassing.dictionary.exceptions.DoesNotExistException;

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
        } while (!command.equals("EXIT"));
    }

    public static void parseCommand(String command) {
        int counter = 1;
        String[] split = command.split(" ");
        if (split.length > 3) {
            printHelp();
            return;
        }
        switch(split[0]) {
            case("KEYS"):
                printStrings(dictionary.getKeys());
                break;
            case("MEMBERS"):
                try {
                    printStrings(dictionary.getMembers(split[1]));
                } catch (DoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case("ADD"):
                try {
                    dictionary.addMember(split[1], split[2]);
                    System.out.println(") Added");
                } catch (AlreadyAddedException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case("REMOVE"):
                try {
                    dictionary.removeMember(split[1], split[2]);
                    System.out.println(") Removed");
                } catch (DoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case("REMOVEALL"):
                try {
                    dictionary.removeKey(split[1]);
                    System.out.println(") Removed");
                } catch (DoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case("CLEAR"):
                dictionary.clearAll();
                System.out.println(") Cleared");
                break;
            case("KEYEXISTS"):
                System.out.println(dictionary.keyExists(split[1]));
                break;
            case("VALUEEXISTS"):
                System.out.println(dictionary.memberExists(split[1], split[2]));
                break;
            case("ALLMEMBERS"):
                printStrings(dictionary.getAllMembers());
                break;
            case("ITEMS"):
                printEntries(dictionary.getEntries());
                break;
            default:
                printHelp();
                break;
        }
    }

    public static void printStrings(List<String> strings) {
        if(strings.isEmpty()) {
            System.out.println(") empty set");
            return;
        }
        int counter = 1;
        for(String string: strings) {
            System.out.println(counter + ") " + string);
            counter++;
        }
    }

    public static void printEntries(List<Entry> entries) {
        int counter = 1;
        for(Entry entry: entries) {
            for(String member: entry.getMembers()) {  
                System.out.println(counter + ") " + entry.getKey() 
                    + ": " + member);
                counter++;
            }
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
