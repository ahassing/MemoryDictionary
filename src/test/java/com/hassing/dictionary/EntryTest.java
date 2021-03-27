package com.hassing.dictionary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;


public class EntryTest {
    private final String KEY = "KEY";
    private final List<String> MEMBERS = new ArrayList<>(Arrays.asList("member1", "member2"));

    private final Entry entry = new Entry();
    
    @Test
    public void testGetAndSetKey() {
        entry.setKey(KEY);
        assertEquals(KEY, entry.getKey());
    }

    @Test
    public void testGetAndSetMembers() {
        entry.setMembers(MEMBERS);
        assertEquals(MEMBERS, entry.getMembers());
    }

    @Test
    public void testAddMember() {
        entry.setMembers(MEMBERS);
        entry.addMember("member3");
        assertTrue(entry.getMembers().contains("member3"));
    }

    @Test
    public void testRemoveMember() {
        entry.setMembers(MEMBERS);
        entry.removeMember("member1");
        assertFalse(entry.getMembers().contains("member1"));
    }
}
