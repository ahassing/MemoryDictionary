package com.hassing.dictionary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class EntryTest {
    public final String KEY = "KEY";
    public List<String> MEMBERS = new ArrayList<>(Arrays.asList("member1", "member2"));

    public final Entry entry = new Entry();
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
