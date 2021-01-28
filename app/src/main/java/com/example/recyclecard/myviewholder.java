package com.example.recyclecard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder {

    TextView img ;
    TextView txt ;

    public myviewholder(@NonNull View itemView) {
        super(itemView);

        img = (TextView) itemView.findViewById(R.id.mname) ;
        txt = (TextView) itemView.findViewById(R.id.textView) ;
    }
}
