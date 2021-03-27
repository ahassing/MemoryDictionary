package com.hassing.dictionary;

import java.util.ArrayList;
import java.util.List;


public class Entry {
    private String key;
    private List<String> members;

    public Entry(String key) {
        this.key = key;
        this.members = new ArrayList<>();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void addMember(String member) {
        members.add(member);
    }

    public void removeMember(String member) {
        members.remove(member);
    }
}
