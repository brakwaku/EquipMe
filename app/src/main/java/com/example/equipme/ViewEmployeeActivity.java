package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ViewEmployeeActivity extends AppCompatActivity {

    ArrayList<String> displayEquipment;
    ArrayList<String> allEquipment;
    ArrayList<Equipment> equipmentArrayList;
    String arrayJSON;
    String employeeData;
    Employee hold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        employeeData = getIntent().getStringExtra("employeeData");
        arrayJSON = getIntent().getStringExtra("equipmentArrayList");

        if (employeeData != null) {
            Gson gson = new Gson();
            final Employee employee = gson.fromJson(employeeData, Employee.class);
            hold = employee;

            //Sets the employee name
            TextView name = (TextView) findViewById(R.id.viewEmployeeNameTextView);
            name.setText(employee.getName());

            //Sets the employee email
            TextView email = (TextView) findViewById(R.id.viewEmployeeEmailTextView);
            email.setText(employee.getEmailAddress());

            //Sets the employee ID Number
            TextView idNumber = (TextView) findViewById(R.id.viewEmployeeNumberTextView);
            idNumber.setText(employee.getEmployeeNumber());

            //Sets the employee Job Title
            TextView jobTitle = (TextView) findViewById(R.id.viewEmployeeJobTitleTextView);
            jobTitle.setText(employee.getJobTitle());

            //Sets the ListView to display the employee's assigned equipment
            if (arrayJSON != null) {
                // Parse ArrayList from JSON
                TypeToken<ArrayList<Equipment>> token = new TypeToken<ArrayList<Equipment>>() {};
                final ArrayList<Equipment> equipmentArrayList = gson.fromJson(arrayJSON, token.getType());
                this.equipmentArrayList = equipmentArrayList;
                displayEquipment = new ArrayList<>();
                allEquipment = new ArrayList<>();
                for (int i = 0; i < employee.getEquipment().size(); i++) {
                    String hold = employee.getEquipment().get(i).getBrand() + " " + employee.getEquipment().get(i).getType();
                    displayEquipment.add(hold);
                }
                for (int all = 0; all < equipmentArrayList.size(); all++) {
                    allEquipment.add(equipmentArrayList.get(all).getDisplayString());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view, R.id.textView, displayEquipment);
                final ListView EquipmentDisplay = (ListView) findViewById(R.id.viewEmployeeAssignedEquipmentListView);
                EquipmentDisplay.setAdapter(arrayAdapter);

                //Sets the spinner to have all available equipment to add to the Employee Object
                Spinner addRemoveEquipment = (Spinner)findViewById(R.id.employeeAddRemoveEquipmentSpinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allEquipment);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addRemoveEquipment.setAdapter(adapter);

                //Sends data to ViewEquipmentActivity so the equipment info can be displayed
                EquipmentDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MainActivity.getmInstanceActivity().viewEquipmentStart(hold.getEquipment().get(i));
                    }
                });
            }

            //Sets the employee notes
            TextView notes = (TextView) findViewById(R.id.viewEmployeeNotesEditText);
            notes.setText(employee.getNotes());
        }
    }
    public void addEquipment(View view){
        Spinner addRemoveEquipment = (Spinner)findViewById(R.id.employeeAddRemoveEquipmentSpinner);
        int position = addRemoveEquipment.getSelectedItemPosition();
        Employee returnedEmployee = MainActivity.getmInstanceActivity().addEquipmentToEmployee(equipmentArrayList.get(position), hold);
        updateListView(returnedEmployee);
    }

    public void removeEquipment(View view){
        Spinner addRemoveEquipment = (Spinner)findViewById(R.id.employeeAddRemoveEquipmentSpinner);
        int position = addRemoveEquipment.getSelectedItemPosition();
        Employee returnedEmployee = MainActivity.getmInstanceActivity().removeEquipmentFromEmployee(equipmentArrayList.get(position), hold);
        updateListView(returnedEmployee);
    }

    public void updateListView(Employee employee){
        displayEquipment = new ArrayList<>();
        allEquipment = new ArrayList<>();
        for (int i = 0; i < employee.getEquipment().size(); i++) {
            String hold = employee.getEquipment().get(i).getBrand() + " " + employee.getEquipment().get(i).getType();
            displayEquipment.add(hold);
        }
        for (int all = 0; all < equipmentArrayList.size(); all++) {
            allEquipment.add(equipmentArrayList.get(all).getDisplayString());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view, R.id.textView, displayEquipment);
        final ListView EquipmentDisplay = (ListView) findViewById(R.id.viewEmployeeAssignedEquipmentListView);
        EquipmentDisplay.setAdapter(arrayAdapter);
        hold = employee;
    }

    public void saveNotes(View view){
        hold = (Employee) MainActivity.getmInstanceActivity().updateNotes(hold, ((EditText)findViewById(R.id.viewEmployeeNotesEditText)).getText().toString());
        Toast.makeText(ViewEmployeeActivity.this, "Notes Saved",Toast.LENGTH_LONG).show();
    }
}

