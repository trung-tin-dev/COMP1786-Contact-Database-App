package com.example.contactdatabaseapp;

import android.os.Bundle;

import android.content.Intent;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactdatabaseapp.adapter.ContactAdapter;
import com.example.contactdatabaseapp.database.DatabaseHelper;
import com.example.contactdatabaseapp.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private ArrayList<Contact> contacts;
    private Button btnAddContact;

    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        btnAddContact = findViewById(R.id.btnAddContact);

        databaseHelper = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadContacts();

        btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        });
    }

    // Load data từ SQLite
    private void loadContacts() {
        contacts = databaseHelper.getAllContacts();

        adapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    // Khi quay lại màn hình thì reload dữ liệu
    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
    }
}