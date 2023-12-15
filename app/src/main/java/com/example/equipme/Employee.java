package com.example.equipme;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Employee extends Displayable {

    String jobTitle;
    String name;
    String emailAddress;
    String employeeNumber;
    ArrayList<Equipment> equipment;
    String notes;

    /**************************************************************************
     * DEFAULT CONSTRUCTOR
     **************************************************************************/
    Employee(){
        equipment = new ArrayList<>();
        setEmployee(true);
    }

    /**************************************************************************
     * NON-DEFAULT CONSTRUCTOR (no equipment)
     **************************************************************************/
    Employee(String name, String jobTitle, String emailAddress, String employeeNumber, String notes) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.emailAddress = emailAddress;
        this.employeeNumber = employeeNumber;
        this.notes = notes;
        equipment = new ArrayList<>();
        setMyKey();
        setEmployee(true);
    }

    /**************************************************************************
     * NON-DEFAULT CONSTRUCTOR (with equipment)
     **************************************************************************/
    Employee(String name, String jobTitle, String emailAddress, String employeeNumber, String notes, ArrayList<Equipment> equipList) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.emailAddress = emailAddress;
        this.employeeNumber = employeeNumber;
        this.notes = notes;
        equipment = equipList;
        setMyKey();
        setEmployee(true);
    }

    void setJobTitle(String jTitle){this.jobTitle = jTitle;}
    String getJobTitle(){return jobTitle;}

    void setName(String NAME){this.name = NAME;}
    String getName(){return name;}

    void setEmailAddress(String email){this.emailAddress = email;}
    String getEmailAddress(){return emailAddress;}

    void setEmployeeNumber(String number){
        this.employeeNumber = number;
        setMyKey();
    }
    String getEmployeeNumber(){return employeeNumber;}

    void setEquipment(ArrayList equip){this.equipment = equip;}

    /**************************************************************************
     * ADD EQUIPMENT
     *
     * Adds the given list of equipment to current list. Sets each equipment's
     * "assignedTo" to this employee's name.
     *
     * @param: equipList - List of equipment to add (can be any size)
     **************************************************************************/
    public void addEquipment(ArrayList<Equipment> equipList) {
        for (int i = 0; i < equipList.size(); i++) {
            equipList.get(i).setAssignedTo(this.name);
            this.equipment.add(equipList.get(i));
        }
    }

    /**************************************************************************
     * REMOVE EQUIPMENT
     *
     * Removes the given list of equipment from current list. Sets each equipment's
     * "assignedTo" to "None".
     *
     * @param: equipList - List of equipment to add (can be any size)
     **************************************************************************/
    public void removeEquipment(ArrayList<Equipment> toRemove) {
        for (int i = 0; i < toRemove.size(); i++) {
            String key = toRemove.get(i).getMyKey();
            boolean found = false;

            for (int j = 0; j < equipment.size() && !found; j++) {
                if (key.equals(equipment.get(j).getMyKey())) {
                    found = true;
                    equipment.remove(j);
                    toRemove.get(i).setAssignedTo("No one assigned");
                }
            }
        }
    }
    ArrayList<Equipment> getEquipment(){return equipment;}

    void setNotes(String note){this.notes = note;}
    String getNotes(){return notes;}

    void display(){}

    /**************************************************************************
     * GET DISPLAY STRING
     *
     * Returns the single string to represent the object in a list
     **************************************************************************/
    @Override
    public String getDisplayString() {
        return name;
    }

    /**************************************************************************
     * SET MY KEY
     *
     * Sets key according to internal values. For employees, it's the
     * employee number
     **************************************************************************/
    @Override
    public void setMyKey() {
        if (employeeNumber != null) {
            myKey = employeeNumber;
        }
    }
}
