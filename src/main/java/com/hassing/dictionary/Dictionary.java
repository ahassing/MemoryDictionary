package com.hassing.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.hassing.dictionary.exceptions.DoesNotExistException;
import com.hassing.dictionary.exceptions.AlreadyAddedException;


public class Dictionary {
    private List<Entry> entries;

    public Dictionary() {
        entries = new ArrayList<>();
    }

    // used for get all items method as well.
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<String> getKeys() {
        List<String> keys = new ArrayList<>(); 
        for(Entry entry : entries) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    public List<String> getMembers(String key) throws DoesNotExistException{ 
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

    public List<String> getAllMembers() {
        List<String> allMembers = new ArrayList<>();
        for(Entry entry: entries) {
            allMembers.addAll(entry.getMembers());
        }
        return allMembers;
    } 
}
