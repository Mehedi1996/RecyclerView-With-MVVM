package com.example.recyclerviewmvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recyclerviewmvvm.R;
import com.example.recyclerviewmvvm.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public RecyclerAdapter(Context context, List<NicePlace> mNicePlace) {
        this.context = context;
        this.mNicePlace = mNicePlace;
    }

    Context context;
     List<NicePlace> mNicePlace = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // set the name of Image
        ((ViewHolder)holder).mName.setText(mNicePlace.get(position).getTitle());

        //image
        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(mNicePlace.get(position).getImageUrl())
                .into(((ViewHolder) holder).mImage);

    }

    @Override
    public int getItemCount() {
        return mNicePlace.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        TextView mName;
        CircleImageView mImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tvImageName);
            mImage = itemView.findViewById(R.id.image);
        }
    }
}
