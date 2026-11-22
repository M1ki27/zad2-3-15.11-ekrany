package com.example.myapplication;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ZgloszenieUsterki";

    private Spinner spCategory;
    private EditText etDescription;
    private RadioGroup rgPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity onCreate");

        spCategory = findViewById(R.id.spCategory);
        etDescription = findViewById(R.id.etDescription);
        rgPriority = findViewById(R.id.rgPriority);

        setupCategorySpinner();

        findViewById(R.id.btnNext).setOnClickListener(v -> validateForm());
    }

    private void setupCategorySpinner() {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.categories,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spCategory.setAdapter(adapter);
    }

    private void validateForm() {
        if (spCategory.getSelectedItemPosition() == 0) {
            Toast.makeText(
                    this,
                    R.string.validation_category,
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        String description = etDescription
                .getText()
                .toString()
                .trim();

        if (description.isEmpty()) {
            etDescription.setError(
                    getString(R.string.validation_description)
            );

            return;
        }

        if (rgPriority.getCheckedRadioButtonId() == -1) {
            Toast.makeText(
                    this,
                    R.string.priority_label,
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Toast.makeText(
                this,
                R.string.form_correct,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity onDestroy");
    }
}