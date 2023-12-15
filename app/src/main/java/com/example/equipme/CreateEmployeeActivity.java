package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CreateEmployeeActivity extends AppCompatActivity {
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
    }

    /**************************************************************************
     * ADD EMPLOYEE TO LIST
     *
     * Called when "Create Employe" button is pressed. Checks that all needed
     * information has been filled properly, then sends a newly created Employee
     * back to the MainActivity
     *
     * @param view - Current view
     **************************************************************************/
    public void addEmployeeToList(View view) {
        if (allInformationFilled()) {
            // Create Employee
            Employee employee = createEmployee();

            // Check for unique ID
            if (idIsUnique(getIntent().getExtras().getStringArrayList("keyList"), employee)) {

                // Convert to JSON string
                Gson gson = new Gson();
                String employeeJson = gson.toJson(employee);

                // Send to MainActivity
                Intent intent = new Intent();
                intent.putExtra("employeeJson", employeeJson);
                setResult(RESULT_OK, intent);

                // Go to MainActivity
                finish();
            } else {
                // ID is taken
                String errorMessage = "Employee ID Number already taken";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            // Information is missing
            String errorMessage = "Some information is still empty";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**************************************************************************
     * ALL INFORMATION FILLED
     *
     * Checks each field in the current view except notes. If any of them are
     * at their default value, returns false. Else returns true.
     **************************************************************************/
    private boolean allInformationFilled() {
        // Check Name EditText
        EditText name = (EditText) findViewById(R.id.employeeNameEditText);
        if (name.getText().toString().equals("")) {
            return false;
        }

        // Check Email EditText
        EditText email = (EditText) findViewById(R.id.employeeEmailEditText);
        if (email.getText().toString().equals("")) {
            return false;
        }

        // Check ID EditText
        EditText id = (EditText) findViewById(R.id.employeeIdEditText);
        if (id.getText().toString().equals("")) {
            return false;
        }

        // Check Job Title Spinner
        Spinner jobTitle = findViewById(R.id.employeeJobTitleSpinner);
        if (jobTitle.getSelectedItemPosition() == 0) {
            return false;
        }

        // Successfully passed all checks, good to go
        return true;
    }

    /**************************************************************************
     * ID IS UNIQUE
     *
     * Checks that the ID entered by the user doesn't already exist in the
     * arraylist of employees.
     **************************************************************************/
    public boolean idIsUnique(ArrayList<String> keyList, Employee createdEmployee) {
        boolean isUnique = true;
        String newKey = createdEmployee.getMyKey();

        for (int i = 0; isUnique && (i < keyList.size()); i++) {
            if (newKey.equals(keyList.get(i))) {
                isUnique = false;
            }
        }

        return isUnique;
    }

    /**************************************************************************
     * CREATE EMPLOYEE
     *
     * Creates a new employee based on information contained in input fields
     *
     * @return employee - The new employee
     **************************************************************************/
    public Employee createEmployee() {
        Employee employee = new Employee();

        // Add name
        EditText name = (EditText) findViewById(R.id.employeeNameEditText);
        employee.setName(name.getText().toString());

        // Add email
        EditText email = (EditText) findViewById(R.id.employeeEmailEditText);
        employee.setEmailAddress(email.getText().toString());

        // Add Employee ID number
        EditText id = (EditText) findViewById(R.id.employeeIdEditText);
        employee.setEmployeeNumber(id.getText().toString());

        // Add Job title
        Spinner jobTitle = (Spinner) findViewById(R.id.employeeJobTitleSpinner);
        employee.setJobTitle(jobTitle.getSelectedItem().toString());

        // Create Empty Equipment ArrayList
        employee.setEquipment(new ArrayList<Equipment>());

        // Add notes
        EditText notes = (EditText) findViewById(R.id.employeeNotesEditText);
        employee.setNotes(notes.getText().toString());

        return employee;
    }
}
