package com.example.recyclecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    Button btn ;
    EditText movie;
    EditText rating;
    Button home;

    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn = findViewById(R.id.button) ;
        movie = findViewById(R.id.movie) ;
        rating = findViewById(R.id.rating) ;
        home = findViewById(R.id.home);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference() ;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String a = movie.getText().toString() ;
                 float b = Float.parseFloat(rating.getText().toString()) ;
                 Model x = new Model() ;
                 x.setDesc(a);
                 x.setImgname(b);
                 dbref.child("rohit").push().setValue(x) ;
                 movie.setText("");
                 rating.setText("");
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class) ;
                startActivity(intent);
            }
        });
    }
}