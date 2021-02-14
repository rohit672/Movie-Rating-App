package com.example.movie_review;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class newRatingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public newRatingFragment() {
        // Required empty public constructor
    }

    String movieName;
    Double rating;
    Map<String,Double> map = new HashMap<>();
    FirebaseUser firebaseUser;
    String location;
    public newRatingFragment(String movieName , Double rating , Map<String,Double> map,String location)  {
         this.movieName = movieName ;
         this.rating = rating ;
         this.map = map;
         this.location = location;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newRatingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static newRatingFragment newInstance(String param1, String param2) {
        newRatingFragment fragment = new newRatingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_rating, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();

//        Boolean haveRated = false ;
//
//        if (map.containsKey(userId)){
//              haveRated = true;
//        }
        TextView textView = view.findViewById(R.id.displayMovie);
        textView.setText(movieName);

        EditText updatedRating = view.findViewById(R.id.newRating);
        Log.i("cnedc", updatedRating.getText().toString());
        Button rate = view.findViewById(R.id.rate) ;

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Double newRating = Double.parseDouble(updatedRating.getText().toString()) ;

                   if (newRating > 10.0) {
                       Toast.makeText(getActivity(),"Cannot Rate More Than 10",Toast.LENGTH_LONG).show();
                       updatedRating.setText("");
                   }
                   else {
                       map.put(userId, newRating);
                       Double sum = (double) 0;
                       for (Double values : map.values()) {
                           sum += (double) values;
                       }
                       double x = (sum) / map.size();
                       x = Math.round(x * 10) / 10.0;

                       Map<String, Object> newData = new HashMap<>();
                       newData.put("movieName", movieName);
                       newData.put("rating", x);
                       newData.put("usersRated", map);
                       FirebaseDatabase.getInstance().getReference().child("rohit")
                               .child(location).updateChildren(newData);
                       onBackPressed();
                   }
            }
        });

        return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new recfragment()).addToBackStack(null).commit();
    }
}