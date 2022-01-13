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

import java.io.Serializable;
import java.util.List;


public class PropirtyAgencyAdabter
        extends RecyclerView.Adapter<PropirtyAgencyAdabter.ViewHolder> {
    private Context context;
    private List<Propirty> items;


    public PropirtyAgencyAdabter(Context context, List<Propirty> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.propcard,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Propirty propirty = items.get(position);
        CardView cardView = holder.cardView;

        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
      //  imageView.setImageDrawable(context.getResources().getDrawable(house));
        TextView city =  cardView.findViewById(R.id.txtcity);
        city.setText("City: "+propirty.getCity());
        //byte[] image = propirty.getPhoto() ;
      //  Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length) ;
        TextView price =  cardView.findViewById(R.id.txtprice);
        price.setText("Price: "+ propirty.getRental_price());
        TextView status =  cardView.findViewById(R.id.txtstatus);
        status.setText(propirty.getStatus());
        TextView surface  =  cardView.findViewById(R.id.txtsurface);
        surface.setText("Surface: "+propirty.getSurface_area());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,detailspropertyAgency.class) ;
                intent.putExtra("id",propirty.getId()) ;
                intent.putExtra("city",propirty.getCity()) ;
                intent.putExtra("garden",propirty.getGarden()) ;
                intent.putExtra("bed",propirty.getNumOfBed()) ;
                intent.putExtra("year",propirty.getConstruction_year()) ;
                intent.putExtra("price",propirty.getRental_price()) ;
                intent.putExtra("balcony",propirty.getBalcony()) ;
                intent.putExtra("postal", propirty.getPostal_address()) ;
                intent.putExtra("discription",  propirty.getDescription()) ;
                intent.putExtra("date",  propirty.getAvailability_date()) ;
                intent.putExtra("agency",  propirty.getAgency_email()) ;
                intent.putExtra("status", propirty.getStatus()) ;
                intent.putExtra("surface",  propirty.getSurface_area()) ;
                intent.putExtra("rented",  propirty.getRented()) ;
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
