package com.example.ivan.apptivate.modelo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.apptivate.R;

import java.util.List;

/**
 * Created by ivan on 05/05/2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.EventoViewHolder> {

    private List<Evento> items;
    //private Context context;

    /*public MyAdapter(Context c) {
        this.context = c;
        mDataset = new ArrayList();
    }

    public void add(Item i) {
        mDataset.add(i);
        notifyItemInserted(mDataset.indexOf(i));
    }
    public void remove(Item item) {
        int position = mDataset.indexOf(item);

        if(position != -1) {
            mDataset.remove(position);
            notifyItemRemoved(position);
        }
    }*/

    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        CardView cv;

        public EventoViewHolder(View v) {
            super(v);
            cv = (CardView) itemView.findViewById(R.id.mycard);
            imagen = (ImageView) v.findViewById(R.id.img);
            nombre = (TextView) v.findViewById(R.id.texto1);
        }
    }

    public CardAdapter(List<Evento> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        EventoViewHolder evh = new EventoViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(EventoViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(R.drawable.img1);
        viewHolder.nombre.setText(items.get(i).getNombre());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

