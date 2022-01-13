package com.example.and;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and.Model.Propirty;
import com.example.and.Model.Rented;

import java.util.List;

public class ViewTenantAdabter extends RecyclerView.Adapter<ViewTenantAdabter.ViewHolder> {
    private Context context;
    private List<Rented> items;


    public ViewTenantAdabter(Context context, List<Rented> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.viewtenantcard, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Rented rented = items.get(position);

        CardView cardView = holder.cardView;
        TextView name = cardView.findViewById(R.id.ViewName);
        name.setText(" " + rented.getFirstname() + " " + rented.getLastname());
        TextView email = cardView.findViewById(R.id.viewEmail);
        email.setText(rented.getEmail());
        TextView postal = cardView.findViewById(R.id.postalView);
        postal.setText("Postal:  " + rented.getPostal());


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewTenantDetails.class);
                intent.putExtra("id", rented.getId());
                intent.putExtra("email", rented.getEmail());
                intent.putExtra("agency_email", rented.getAgency_email());
                intent.putExtra("first", rented.getFirstname());
                intent.putExtra("last", rented.getLastname());
                intent.putExtra("postal", rented.getPostal());
                intent.putExtra("status", rented.getStatus());
                intent.putExtra("salary", rented.getSalary());
                intent.putExtra("family", rented.getFamily());
                intent.putExtra("phone", rented.getPhone());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;

        }


    }
}

