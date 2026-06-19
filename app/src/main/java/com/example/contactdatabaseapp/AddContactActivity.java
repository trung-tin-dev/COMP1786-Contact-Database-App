package com.example.contactdatabaseapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contactdatabaseapp.database.DatabaseHelper;

public class AddContactActivity extends AppCompatActivity {

    EditText edtName, edtPhone, edtEmail;
    RadioGroup rgAvatar;
    Button btnSave;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DatabaseHelper(this);

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        rgAvatar = findViewById(R.id.rgAvatar);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {

            String name = edtName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();

            // ❌ VALIDATION
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int avatar = R.drawable.avatar1;

            int checkedId = rgAvatar.getCheckedRadioButtonId();

            if (checkedId == -1) {
                Toast.makeText(this, "Please select avatar", Toast.LENGTH_SHORT).show();
                return;
            }

            if (checkedId == R.id.rbAvatar2) avatar = R.drawable.avatar2;
            else if (checkedId == R.id.rbAvatar3) avatar = R.drawable.avatar3;
            else if (checkedId == R.id.rbAvatar4) avatar = R.drawable.avatar4;

            boolean inserted = db.addContact(name, phone, email, avatar);

            if (inserted) {
                Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}