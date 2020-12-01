package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room.Room.Alumn;
import com.example.room.Room.AlumnLab;
import com.example.room.Room.AppDatabaseRoom;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AlumnLab alumnLab;
    Alumn alumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CONFIGURE
        alumnLab = AlumnLab.get(this);
        //METHODS
        addActivity();
        //alumnLab.deleteMigration(this);
        getAllAlumns();

    }

    protected void getAllAlumns(){
        try {
            alumnLab.deleteAllAlumns();
            List<Alumn> alumns = alumnLab.getAlumns();
            Toast.makeText(this, "" + alumns.size(), Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    protected void addActivity(){
        Button btAdd = (Button) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, AddFullscreenActivity.class);
                startActivity(intent);
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}