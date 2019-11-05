package com.mcs.luel.myweather;

import android.net.Uri;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mcs.luel.myweather.PojoClasses.PeriodDetailPojo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class DailyCustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    List<PeriodDetailPojo> dataSet;

    public DailyCustomAdapter(List<PeriodDetailPojo> dataSet) {
        this.dataSet = dataSet;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_layout,
                        parent,
                        false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.temp.setText(dataSet.get(position).getTemperature() + "\u2109");
        String name;
        if(dataSet.get(position).getName().isEmpty()){
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
            LocalDateTime date = LocalDateTime.parse(dataSet.get(position).getStartTime(), inputFormatter);
            name = outputFormatter.format(date);
        }

        else {
            name = dataSet.get(position).getName();
        }
        holder.time.setText(name);
        Uri url = Uri.parse(dataSet.get(position).getIcon());
        Glide.with(holder.iv.getContext()).load(url).apply(RequestOptions.circleCropTransform()).into(holder.iv);



//        requestOptions = requestOptions.centerCrop().circleCrop();.transforms(new CenterCrop(), new RoundedCorners(16));
//        Glide.with(itemView.getContext())
//                .load(item.getImage())
//                .apply(requestOptions)
//                .into(mProgramThumbnail);

    }

    @Override
    public int getItemCount() {
        return 14;
    }
}
