package com.hassing.dictionary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.SupportedAnnotationTypes;

import com.hassing.dictionary.exceptions.AlreadyAddedException;
import com.hassing.dictionary.exceptions.DoesNotExistException;

import org.junit.Before;
import org.junit.Test;

public class DictionaryTest {

    private Dictionary dictionary;

    private Entry entry1;
    private Entry entry2;

    @Before
    public void init() {
        dictionary = new Dictionary();
        entry1 = new Entry();
        entry2 = new Entry();
    }

    @Test
    public void testGetAndSetEntries() {
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1, entry2));
        dictionary.setEntries(entries);
        assertEquals(entries, dictionary.getEntries());
    }

    @Test
    public void testGetKeys() {
        entry1.setKey("key1");
        entry2.setKey("key2");
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1, entry2));
        dictionary.setEntries(entries);
        List<String> keys = new ArrayList<>(Arrays.asList("key1", "key2"));
        assertEquals(keys, dictionary.getKeys());
    }

    @Test
    public void testGetMembers() throws DoesNotExistException {
        entry1.setKey("key1");
        List<String> members = new ArrayList<>(Arrays.asList("members1", "members2"));
        entry1.setMembers(members);
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        assertEquals(members, dictionary.getMembers("key1"));
    }

    @Test(expected = DoesNotExistException.class)
    public void testGetMembersWithException() throws DoesNotExistException {
        dictionary.getMembers("key1");
    }

    @Test
    public void testAddMemberAlreadyExistingKey() throws AlreadyAddedException, DoesNotExistException {
        entry1.setKey("key1");
        List<String> members = new ArrayList<>(Arrays.asList("members1"));
        entry1.setMembers(members);
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        dictionary.addMember("key1", "members2");
        List<String> expected = new ArrayList<>(Arrays.asList("members1", "members2"));
        assertEquals(expected, dictionary.getMembers("key1"));
    }

    @Test
    public void testAddMemberEmptyDictionary() throws AlreadyAddedException, DoesNotExistException {
        dictionary.addMember("key1", "members2");
        List<String> expected = new ArrayList<>(Arrays.asList("members2"));
        assertEquals(expected, dictionary.getMembers("key1"));
    }

    @Test(expected = AlreadyAddedException.class)
    public void testAddMemberAlreadyExists() throws AlreadyAddedException, DoesNotExistException {
        entry1.setKey("key1");
        List<String> members = new ArrayList<>(Arrays.asList("members1"));
        entry1.setMembers(members);
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        dictionary.addMember("key1", "members1");
    }

    @Test
    public void testRemoveMembers() throws DoesNotExistException {
        entry1.setKey("key1");
        List<String> members = new ArrayList<>(Arrays.asList("members1", "members2"));
        entry1.setMembers(members);
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        dictionary.removeMember("key1", "members1");
        List<String> expected = new ArrayList<>(Arrays.asList("members2"));
        assertEquals(expected, dictionary.getMembers("key1"));
    }

    @Test
    public void testRemoveMemberAndKey() throws DoesNotExistException {
        entry1.setKey("key1");
        List<String> members = new ArrayList<>(Arrays.asList("members1"));
        entry1.setMembers(members);
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        dictionary.removeMember("key1", "members1");
        assertTrue(dictionary.getKeys().isEmpty());
    }

    @Test(expected = DoesNotExistException.class)
    public void testRemoveMemberAndKeyDoesNotExist() throws DoesNotExistException {
        dictionary.removeMember("key1", "members1");
    }

    @Test(expected = DoesNotExistException.class)
    public void testRemoveMemberAndMemberDoesNotExist() throws DoesNotExistException {
        entry1.setKey("key1");
        List<String> members = new ArrayList<>(Arrays.asList("members1"));
        entry1.setMembers(members);
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        dictionary.removeMember("key1", "members2");
    }

    @Test
    public void testRemoveKey() throws DoesNotExistException {
        entry1.setKey("key1");
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        dictionary.removeKey("key1");
        assertTrue(dictionary.getKeys().isEmpty());
    }

    @Test(expected = DoesNotExistException.class)
    public void testRemoveKeyDoesNotExist() throws DoesNotExistException {
        dictionary.removeKey("key1");
    }

    @Test
    public void testClearAll() {
        entry1.setKey("key1");
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        dictionary.clearAll();
        assertTrue(dictionary.getEntries().isEmpty());
    }

    @Test
    public void testKeyExistsTrue() {
        entry1.setKey("key1");
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        assertTrue(dictionary.keyExists("key1"));
    }

    @Test
    public void testKeyExistsFalse() {
        assertFalse(dictionary.keyExists("key1"));
    }

    @Test
    public void testMemberExistsTrue() {
        entry1.setKey("key1");
        List<String> members = new ArrayList<>(Arrays.asList("members1"));
        entry1.setMembers(members);
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1));
        dictionary.setEntries(entries);
        assertTrue(dictionary.memberExists("key1", "members1"));
    }

    @Test
    public void testMemberExistsFalse() {
        assertFalse(dictionary.memberExists("key1", "member1"));
    }

    @Test
    public void testGetAllMembrs() {
        entry1.setKey("key1");
        List<String> members1 = new ArrayList<>(Arrays.asList("members1"));
        entry1.setMembers(members1);
        entry2.setKey("key2");
        List<String> members2 = new ArrayList<>(Arrays.asList("members2"));
        entry2.setMembers(members2);
        List<Entry> entries = new ArrayList<>(Arrays.asList(entry1, entry2));
        dictionary.setEntries(entries);
        List<String> expected = new ArrayList<>(Arrays.asList("members1", "members2"));
        assertEquals(expected, dictionary.getAllMembers());
    }
}  
