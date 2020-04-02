package com.example.trioplanner.histroy_view;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.trioplanner.R;
import com.example.trioplanner.Uitiles;
import com.example.trioplanner.data.Trip;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.trioplanner.Uitiles.TAG;

public class HistroyAdapter extends RecyclerView.Adapter<HistroyAdapter.HistroyViewHolder> {

    Context context;
    View forDialoge;
    // List<String> names;
    List<Trip> trips = new ArrayList<>();

    public HistroyAdapter(Context context) {
        this.context = context;
    }

    public void setTripsList(List<Trip> trips) {
        this.trips = trips;
        Log.i(TAG, "HistroyAdapter >> setTripsList: tripsSize()" + trips.size());
    }

    @NonNull
    @Override
    public HistroyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_row_history, parent, false);
        HistroyAdapter.HistroyViewHolder viewHolder = new HistroyAdapter.HistroyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistroyViewHolder holder, int position) {
        Trip aTrip = trips.get(position);
        holder.tvTripName.setText(aTrip.getName());
        holder.tvStatus.setText(aTrip.getStatus());
        holder.tvTripDate.setText(aTrip.getDate());
        holder.tvTripTime.setText(aTrip.getTime());
        holder.tvTripStartLoc.setText(aTrip.getStartLoc());
        holder.tvTripEndLoc.setText(aTrip.getEndLoc());

        if (aTrip.getStatus().equals(Uitiles.STATUS_DONE)){
            holder.ivStatus.setImageResource(R.drawable.ic_check_black_24dp);
        } else {
            holder.ivStatus.setImageResource(R.drawable.ic_close_black_24dp);
        }



        holder.consSingleRow.setOnClickListener(v -> {

            Uitiles.showCustomDialog(forDialoge, aTrip.getNotes(), context);
       });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    class HistroyViewHolder extends ViewHolder {
        View consSingleRow;
        TextView tvTripName;
        TextView tvStatus;
        TextView tvTripDate;
        TextView tvTripTime;
        TextView tvTripStartLoc;
        TextView tvTripEndLoc;
        ImageView ivStatus;

        public HistroyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTripName = itemView.findViewById(R.id.tvTripName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTripDate = itemView.findViewById(R.id.tvTripDate);
            tvTripTime = itemView.findViewById(R.id.tvTripTime);
            tvTripStartLoc = itemView.findViewById(R.id.tvStartLoc);
            tvTripEndLoc = itemView.findViewById(R.id.tvEndLoc);
            ivStatus = itemView.findViewById(R.id.ivStatus);
            forDialoge = itemView;
            consSingleRow = itemView.findViewById(R.id.singleRowHome);

        }
    }




}

