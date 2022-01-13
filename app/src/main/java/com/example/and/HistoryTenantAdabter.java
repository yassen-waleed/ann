
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
        import com.example.and.Model.TeneantHistory;

        import java.util.List;

public class HistoryTenantAdabter extends RecyclerView.Adapter<HistoryTenantAdabter.ViewHolder> {
    private Context context;
    private List<TeneantHistory> items;


    public HistoryTenantAdabter(Context context, List<TeneantHistory> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.historycard, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TeneantHistory history = items.get(position);

        CardView cardView = holder.cardView;
        TextView email = cardView.findViewById(R.id.history_email);
        email.setText("Agency email: " + history.getAgency_email() );
        TextView city = cardView.findViewById(R.id.history_city);
        city.setText("property City: "+history.getCity());
        TextView postal = cardView.findViewById(R.id.history_postal);
        postal.setText("Postal:  " + history.getPostal());
        TextView status = cardView.findViewById(R.id.history_status);
        status.setText("status:  " + history.getStatus());
        TextView price = cardView.findViewById(R.id.history_price);
        price.setText("rent price:  " + history.getRental_price());

        TextView date = cardView.findViewById(R.id.history_date);
        date.setText(" " + history.getStart()+"   to   "+history.getEnd());
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

