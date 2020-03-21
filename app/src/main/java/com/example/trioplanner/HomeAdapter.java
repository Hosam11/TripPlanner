package com.example.trioplanner;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    Context context;
    List<String> names;

    public HomeAdapter(Context context , List<String> names) {
        this.context = context;
        this.names = names;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_row_home, parent, false);
        HomeViewHolder viewHolder = new HomeViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.tvTripName.setText(names.get(position));
        holder.consSingleRow.setOnClickListener(v ->
                Toast.makeText(context, "clicked show notes ", Toast.LENGTH_SHORT).show());

        holder.consSingleRow.setOnLongClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "long clicked edit trip ", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {


        View consSingleRow;
        TextView tvTripName;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTripName = itemView.findViewById(R.id.tvTripName);
            consSingleRow = itemView.findViewById(R.id.singleRowHome);
        }
    }

}
