package com.example.customviewroomdemo1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    List<Place> list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;


    public PlaceAdapter(List<Place> list, Context context) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new PlaceViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.PlaceViewHolder holder, int position) {
        Place place = list.get(position);
        holder.tvName.setText(place.getName());

        AppDatabase db = Room.databaseBuilder(
                context,
                AppDatabase.class,
                "PlaceDB"
        ).allowMainThreadQueries().build();

        PlaceDao placeDao = db.placeDao();

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(position).getId();
                Bundle bundle = new Bundle();
                String str = bundle.getString("placeName");
                placeDao.updatePlace("Updated", id);
                Toast.makeText(context, "Thành Công", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeDao.deletePlace(list.get(position));
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());

                Toast.makeText(context, "Thành Công", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageButton btnUpdate, btnRemove;
        PlaceAdapter placeAdapter;
        public PlaceViewHolder(@NonNull View view, PlaceAdapter placeAdapter) {
            super(view);

            tvName = view.findViewById(R.id.tvName);
            btnUpdate = view.findViewById(R.id.btnUpdate);
            btnRemove = view.findViewById(R.id.btnRemove);
            this.placeAdapter = placeAdapter;
        }
    }
}
