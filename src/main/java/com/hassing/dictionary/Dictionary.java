package com.hassing.dictionary;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Entry> entries;

    public Dictionary() {
        entries = new ArrayList<>();
    }

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

    public ArrayList<String> getMembers(String key) throws NoKeysException{ 
        for(Entry entry : entries) {
            if(key.equals(entry.getKey())) {
                return entry.getMembers();
            }
        }
        throw new NoKeysException("ERROR: key does not exist");
    }

    public void addEntry(Entry entry) throws AlreadyAddedException{
        if(entries.contains(entry)) {
            throw new AlreadyAddedException("ERROR: value already exists");
        } 
        entries.add(entry);
    }
}
