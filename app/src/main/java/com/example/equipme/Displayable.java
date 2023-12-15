package com.example.equipme;

public class Displayable {
    protected String myKey;
    private boolean isEmployee;

    Displayable() {
        setMyKey();
        setEmployee(false);
    }

    public void setEmployee(boolean isEmployee) { this.isEmployee = isEmployee; }
    public boolean isEmployee() { return isEmployee; }

    /**************************************************************************
     * SET MY KEY
     *
     * Sets key according to internal values
     **************************************************************************/
    public void setMyKey() { myKey = ""; }
    public String getMyKey() { return myKey; }

    /**************************************************************************
     * GET DISPLAY STRING
     *
     * Returns the single string to represent the object in a list
     **************************************************************************/
    public String getDisplayString() {
        return "NO TYPE";
    }
}
