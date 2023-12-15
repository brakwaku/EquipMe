package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class ViewEquipmentActivity extends AppCompatActivity {

    Equipment hold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_equipment);

        String equipmentString = getIntent().getStringExtra("equipmentData");
        if(equipmentString != null) {
            Gson gson = new Gson();
            Equipment singleEquipment = gson.fromJson(equipmentString, Equipment.class);
            hold = singleEquipment;
            //Set the value for the equipment assigned to
            TextView assignedTo = (TextView)findViewById(R.id.viewEquipmentAssignedToTextView);
            assignedTo.setText(singleEquipment.getAssignedTo());

            //Set the value for the equipment assigned date
            TextView assignedDate = (TextView)findViewById(R.id.viewEquipmentDateTextView);
            assignedDate.setText(singleEquipment.getDateGiven().toString());

            //Set the value for the equipment brand
            TextView equipmentBrand = (TextView)findViewById(R.id.equipmentBrandTextView);
            equipmentBrand.setText(singleEquipment.getBrand());

            //Set the value for the equipment other notes
            TextView equipmentNotes = (TextView)findViewById(R.id.equipmentNotesEditText); //This should be a TextView and not an EditView
            equipmentNotes.setText(singleEquipment.getNotes());

            //Set the value for the equipment type
            TextView equipmentType = (TextView)findViewById(R.id.viewEquipmentTypeTextView); //ID is temporal. TextView needs to be created
            equipmentType.setText(singleEquipment.getType());

            //Set the value for the equipment serial number
            TextView equipmentSerialNumber = (TextView)findViewById(R.id.viewEquipmentSerialTextView);  //ID is temporal. TextView needs to be created
            equipmentSerialNumber.setText(singleEquipment.getSerialNumber());
        }

    }

    public void saveNotes(View view){
        hold = (Equipment) MainActivity.getmInstanceActivity().updateNotes(hold, ((EditText)findViewById(R.id.equipmentNotesEditText)).getText().toString());
        Toast.makeText(ViewEquipmentActivity.this, "Notes Saved",Toast.LENGTH_LONG).show();
    }
}
