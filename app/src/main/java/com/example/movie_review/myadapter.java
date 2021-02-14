package com.example.movie_review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<Model , myadapter.myviewholder> {


    public myadapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull Model model) {

          myviewholder.movie.setText(model.getMovieName());
          myviewholder.rating.setText(Double.toString(model.getRating()));

          myviewholder.movie.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {


                  String location ;
                  location = getRef(i).getKey();
//                  FirebaseDatabase.getInstance().getReference().child("rohit")
//                          .child(getRef(i).getKey());
                  AppCompatActivity activity = (AppCompatActivity)v.getContext();
                  activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new newRatingFragment(model.getMovieName(),model.getRating(),model.getUsersRated(),location)).addToBackStack(null).commit();
              }
          });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
          return new myviewholder(view) ;

    }

    class myviewholder extends RecyclerView.ViewHolder{

           TextView movie ;
           TextView rating ;

           public myviewholder(@NonNull View itemView) {
               super(itemView);
               movie = (TextView)itemView.findViewById(R.id.textView);
               rating = (TextView)itemView.findViewById(R.id.mname);
           }
       }

}
