package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateEquipmentActivity extends AppCompatActivity {
    Button equipmentCreateButton;  // Button to create new equipment and go to equipment activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_equipment);

        equipmentCreateButton = findViewById(R.id.equipmentCreateButton);

        equipmentCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allInformationFilled()) {

                    // Populate dropdown
                    /************************************************************************************************************
                     Spinner equipTypeSpinner = (Spinner) findViewById(R.id.equipmentTypeSpinner);
                     ArrayAdapter<String> equipTypeAdapter = new ArrayAdapter<String>(CreateEquipmentActivity.this,
                     android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.equipment_type_options));
                     equipTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                     Spinner equipBrandSpinner = (Spinner) findViewById(R.id.equipmentBrandSpinner);
                     ArrayAdapter<String> equipBrandAdapter = new ArrayAdapter<String>(CreateEquipmentActivity.this,
                     android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.equipment_brand_options));
                     equipBrandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     // Allows the data to show inside the spinner
                     equipTypeSpinner.setAdapter(equipTypeAdapter);
                     String type = equipTypeSpinner.getSelectedItem().toString();


                     // Populate "Brand of equipment" dropdown
                     equipBrandSpinner.setAdapter(equipBrandAdapter);
                     String brand = equipBrandSpinner.getSelectedItem().toString();

                     */

                    // Populate "Equipment type" dropdown
                    Spinner type = (Spinner) findViewById(R.id.equipmentTypeSpinner);

                    // Populate "Equipment brand" dropdown
                    Spinner brand = (Spinner) findViewById(R.id.equipmentBrandSpinner);

                    // User input for "Serial Number"
                    EditText serialNumber = (EditText) findViewById(R.id.equipmentSerialEditText);

                    // User input for "Other notes"
                    EditText notes = (EditText) findViewById(R.id.equipmentNotesEditText);


                    /*
                     * Equipment creation code and logic here
                     * */
                    Equipment newEquipment = new Equipment();
                    newEquipment.setType(type.getSelectedItem().toString());
                    newEquipment.setBrand(brand.getSelectedItem().toString());
                    newEquipment.setSerialNumber(serialNumber.getText().toString());
                    newEquipment.setNotes(notes.getText().toString());

                    //First parameter of the Intent is where you are going from and the 2nd is your destination
                    Intent createEquipmentIntent = new Intent(CreateEquipmentActivity.this, MainActivity.class);

                    createEquipmentIntent.putExtra("equipment", newEquipment);

                    setResult(RESULT_OK, createEquipmentIntent);

                    finish();
                } else {
                    // Information is missing
                    String errorMessage = "Some information is still empty";
                    Toast toast = Toast.makeText(CreateEquipmentActivity.this, errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    /**************************************************************************
     * ALL INFORMATION FILLED
     *
     * Checks each field in the current view except notes. If any of them are
     * at their default value, returns false. Else returns true.
     **************************************************************************/
    private boolean allInformationFilled() {
        // Check Equipment type Spinner
        Spinner equipmentType = findViewById(R.id.equipmentTypeSpinner);
        if (equipmentType.getSelectedItemPosition() == 0) {
            return false;
        }

        // Check Equipment brand Spinner
        Spinner equipmentBrand = findViewById(R.id.equipmentBrandSpinner);
        if (equipmentBrand.getSelectedItemPosition() == 0) {
            return false;
        }

        // Check Serial Number EditText
        EditText serialNumber = (EditText) findViewById(R.id.equipmentSerialEditText);
        if (serialNumber.getText().toString().equals("")) {
            return false;
        }

        // Successfully passed all checks, good to go
        return true;
    }
}
