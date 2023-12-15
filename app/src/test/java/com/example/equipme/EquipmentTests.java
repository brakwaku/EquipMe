package com.example.equipme;

import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertEquals;

public class EquipmentTests {
    @Test
    public void setTypeTest() throws NoSuchFieldException {
        try {
            Equipment equipment = new Equipment();
            equipment.setType("brandA");

            Field typeField = equipment.getClass().getDeclaredField("type");
            typeField.setAccessible(true);

            assertEquals("brandA", typeField.get(equipment));
        } catch (Exception e) {

        }
    }

    @Test
    public void setAssignedToTest() throws NoSuchFieldException {
        try {
            Equipment equipment = new Equipment();
            equipment.setAssignedTo("Major Mike");

            Field typeField = equipment.getClass().getDeclaredField("assignedTo");
            typeField.setAccessible(true);

            assertEquals("Major Mike", typeField.get(equipment));
        } catch (Exception e) {

        }
    }

    @Test
    public void setNotesTest() throws NoSuchFieldException {
        try {
            Equipment equipment = new Equipment();
            equipment.setNotes("This employee owes me $20");

            Field typeField = equipment.getClass().getDeclaredField("notes");
            typeField.setAccessible(true);

            assertEquals("This employee owes me $20", typeField.get(equipment));
        } catch (Exception e) {

        }
    }
}
