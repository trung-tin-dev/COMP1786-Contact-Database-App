package com.example.contactdatabaseapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactdatabaseapp.R;
import com.example.contactdatabaseapp.model.Contact;

import java.util.ArrayList;

public class ContactAdapter
        extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<Contact> contactList;

    public ContactAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_contact,
                                parent,
                                false
                        );

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ContactViewHolder holder,
            int position
    ) {

        Contact contact =
                contactList.get(position);

        holder.txtName.setText(
                contact.getName()
        );

        holder.txtPhone.setText(
                contact.getPhone()
        );

        holder.txtEmail.setText(
                contact.getEmail()
        );

        holder.imgAvatar.setImageResource(
                contact.getAvatar()
        );
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView txtName;
        TextView txtPhone;
        TextView txtEmail;

        public ContactViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);

            imgAvatar =
                    itemView.findViewById(
                            R.id.imgAvatar
                    );

            txtName =
                    itemView.findViewById(
                            R.id.txtName
                    );

            txtPhone =
                    itemView.findViewById(
                            R.id.txtPhone
                    );

            txtEmail =
                    itemView.findViewById(
                            R.id.txtEmail
                    );
        }
    }
}