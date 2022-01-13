package com.example.and;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and.Model.AgencyHistory;

import java.util.List;

public class AgencyHistoryAdabter extends RecyclerView.Adapter<AgencyHistoryAdabter.ViewHolder> {
    private Context context;
    private List<AgencyHistory> items;


    public AgencyHistoryAdabter(Context context, List<AgencyHistory> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public AgencyHistoryAdabter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.history1card, parent, false);
        return new AgencyHistoryAdabter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AgencyHistoryAdabter.ViewHolder holder, int position) {
        final AgencyHistory history = items.get(position);

        CardView cardView = holder.cardView;
        TextView email = cardView.findViewById(R.id.Ahistory_email);
        email.setText("tenant email: " + history.getTenant_email() );
        TextView city = cardView.findViewById(R.id.Ahistory_city);
        city.setText("property City: "+history.getCity());
        TextView postal = cardView.findViewById(R.id.ahistory_postal);
        postal.setText("Postal:  " + history.getPostal());
        TextView date = cardView.findViewById(R.id.Ahistory_date);
        date.setText(" " + history.getStart()+"   to   "+history.getEnd());
        TextView name = cardView.findViewById(R.id.Ahistory_name);
        name.setText("Tenant name:  " + history.getFirst_name()+" "+history.getLast_name());
        TextView id = cardView.findViewById(R.id.Ahistory_id);
        id.setText("House id:  " + history.getHouse_id());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
