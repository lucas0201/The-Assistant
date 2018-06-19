package com.example.pm.assistant;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pm.assistant.assistant.AssistantMain;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private TextView mTextMessage;
    private String user;


    private String newContactName;
    private String newContactRelationship;

    private AssistantMain assistant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        user = intent.getStringExtra("id");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.navigation_contact);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_add:
                fragment = new AddContactFragment();
                break;
            case R.id.navigation_contact:
                fragment = new ContactsFragment();
                break;
            case R.id.navigation_profile:
                fragment = new UserProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }

    public void goToEditProfile(View v){
        Toast toast = Toast.makeText(this, "Edit", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("id", user);
        startActivity(intent);
    }

    public void addContact(View v){
        EditText newName = (EditText) findViewById(R.id.nameAddEditText);
        EditText newRelationship = (EditText) findViewById(R.id.relationshipAddEditText);

        newContactName = newName.getText().toString();
        newContactRelationship = newRelationship.getText().toString();

        if(newContactName.equals("") || newContactRelationship.equals("")){
            Toast toast = Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG);
            toast.show();
        }else{
            // adicionar no banco de dados
            Toast toast = Toast.makeText(this, "Adicionado com sucesso", Toast.LENGTH_LONG);
            toast.show();

            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(this);
            navigation.setSelectedItemId(R.id.navigation_contact);
        }
    }

    public void addPhoto(View v){
        Toast toast = Toast.makeText(this, "Foto Adicionada", Toast.LENGTH_LONG);
        toast.show();
    }


}

