package com.example.architecture_components;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Edit_note extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.architecture_components.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.architecture_components.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.architecture_components.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.architecture_components.EXTRA_PRIORITY";


    EditText edittitle;
    EditText editdescription;
    NumberPicker numberPicker;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edittitle = (EditText) findViewById(R.id.edit_text_title);
        editdescription = (EditText) findViewById(R.id.edit_text_description);
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);

        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(0);
    }

    private void savenote(){
        String title = edittitle.getText().toString();
        String description = editdescription.getText().toString();
        int priority = numberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                savenote();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
