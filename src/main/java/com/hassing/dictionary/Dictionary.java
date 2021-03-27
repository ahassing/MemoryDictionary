package com.hassing.dictionary;

import java.util.ArrayList;

import com.hassing.dictionary.exceptions.DoesNotExistException;
import com.hassing.dictionary.exceptions.AlreadyAddedException;


public class Dictionary {
    private ArrayList<Entry> entries;

    public Dictionary() {
        entries = new ArrayList<>();
    }

    // used for get all items method as well.
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public ArrayList<String> getKeys() {
        ArrayList<String> keys = new ArrayList<>(); 
        for(Entry entry : entries) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    public ArrayList<String> getMembers(String key) throws DoesNotExistException{ 
        for(Entry entry : entries) {
            if(key.equals(entry.getKey())) {
                return entry.getMembers();
            }
        }
        throw new DoesNotExistException("ERROR, key does not exist");
    }

    public void addMember(String key, String member) throws AlreadyAddedException{
        for(Entry entry: entries) {
            if(entry.getKey().equals(key)) {
                if(entry.getMembers().contains(member)){
                    throw new AlreadyAddedException("ERROR, value already exists");
                } else {
                    entry.addMember(member);
                    return;
                }
            }
        }
        // Key is not present in dictionary
        Entry newEntry = new Entry(key);
        newEntry.addMember(member);
        entries.add(newEntry);
    }

    public void removeMember(String key, String member) throws DoesNotExistException {
        for(Entry entry: entries) {
            if(entry.getKey().equals(key)) {
                if(entry.getMembers().contains(member)){
                    entry.removeMember(member);
                    return;
                } else {
                    throw new DoesNotExistException("ERROR, value does not exist");
                }
            }
        }
        throw new DoesNotExistException("ERROR, key does not exist");
    }

    public void removeKey(String key) {
        for(Entry entry: entries) {
            if(entry.getKey().equals(key)) {
                entries.remove(entry);
                return;
            }
        }
        throw new DoesNotExistException("ERROR, key does not exist");
    }

    public void clearAll() {
        entries = new ArrayList<>();
    }

    public boolean keyExists(String key) {
        for(Entry entry: entries) {
            if(entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }
    
    public boolean memberExists(String key, String member) {
        for(Entry entry: entries) {
            if(entry.getKey().equals(key)){
                return entry.getMembers().contains(member);
            }
        }
        return false;
    }

    public ArrayList<String> getAllMembers() {
        ArrayList<String> allMembers = new ArrayList<>();
        for(Entry entry: entries) {
            allMembers.addAll(entry.getMembers());
        }
        return allMembers;
    } 
}
