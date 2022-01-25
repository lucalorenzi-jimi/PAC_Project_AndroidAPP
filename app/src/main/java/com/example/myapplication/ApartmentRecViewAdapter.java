package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ApartmentRecViewAdapter extends RecyclerView.Adapter<ApartmentRecViewAdapter.ViewHolder> {

    private static final String TAG = "ApartmentViewAdapter";

    private ArrayList<Apartment> apartments = new ArrayList<>();
    private Context mContext;
    private ViewHolder holder;
    private int position;

    public ApartmentRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_apartment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewOlder: Called");
        holder.txtName.setText(apartments.get(position).getName());
        holder.txtAddress.setText((apartments.get(position).getLocation()));
        holder.txtPrice.setText((apartments.get(position).getPricePerNight().toString()));
        Glide.with(mContext).asBitmap().load(apartments.get(position).getImageUrl()).into(holder.imgApartment);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, /*apartments.get(position).getName() + */ " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return apartments.size();
    }

    public void setApartments(ArrayList<Apartment> apartments) {
        this.apartments = apartments;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imgApartment;
        private TextView txtName, txtAddress, txtPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgApartment = itemView.findViewById(R.id.imageApartment);
            txtName = itemView.findViewById(R.id.nameApartment);
            txtAddress = itemView.findViewById(R.id.apptAddress);
            txtPrice = itemView.findViewById(R.id.priceNight);
        }
    }
}
