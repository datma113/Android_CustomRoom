package com.example.customviewroomdemo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Place> list = new ArrayList<>();
    PlaceAdapter placeAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "PlaceDB"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        PlaceDao placeDao = db.placeDao();
        EditText txt = findViewById(R.id.edtxtPlace);
        Bundle bundle = new Bundle();
        bundle.putString("placeName", txt.getText().toString());

        list = placeDao.getAllPlace();
        recyclerView = findViewById(R.id.rcvPlace);

        placeAdapter = new PlaceAdapter(list, this);
        recyclerView.setAdapter(placeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt = findViewById(R.id.edtxtPlace);
                String name = txt.getText().toString();
                Place p = new Place(name);
                placeDao.insertPlace(p);
                txt.setText("");

                list = placeDao.getAllPlace();
                placeAdapter = new PlaceAdapter(list, MainActivity.this);
                recyclerView.setAdapter(placeAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            }
        });

    }
}