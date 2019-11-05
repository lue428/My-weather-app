package com.mcs.luel.myweather;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CustomViewHolder extends RecyclerView.ViewHolder {

    ImageView iv;
    TextView temp, time;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.tv_time);
        temp = itemView.findViewById(R.id.tv_temp_value);
        iv = itemView.findViewById(R.id.iv_condition);
    }
}
