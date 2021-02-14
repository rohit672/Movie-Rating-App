package com.example.movie_review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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
        String userId = firebaseUser.getUid();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference() ;
        Map<String, Double> map = new HashMap<>();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String a = movie.getText().toString() ;
                 Double b = Double.parseDouble(rating.getText().toString()) ;

                 if (b > 10.0) {

                     Toast.makeText(getApplicationContext(),"Cannot Rate More Than 10",Toast.LENGTH_LONG).show();
                     movie.setText("");
                     rating.setText("");
                 }
                 else {
                     Model x = new Model();
                     x.setMovieName(a);
                     x.setRating(b);
                     map.put(userId, b);
                     x.setUsersRated(map);
                     dbref.child("rohit").push().setValue(x);
//                 String y = userId + "/" + a ;
//                 FirebaseDatabase.getInstance().getReference(y).setValue(b);
                     movie.setText("");
                     rating.setText("");
                 }
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