package com.techpurush.commonandroidutility.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.techpurush.commonandroidutility.R;

import java.util.Map;


public class TwoItemsAdapter extends RecyclerView.Adapter<TwoItemsAdapter.ViewHolder> {
    Context context;
    Map<String, String> data;

    public TwoItemsAdapter(Context context, Map<String, String> data) {
        this.context = context;
        this.data = data;
    }

    String colorss[] = {"#FF0000", "#FF8000", "#7401DF", "#80FF00", "#00FFFF", "#0000FF", "#000000"};
    int i = 0;

    String getColor() {

        String clr[] = {
                "#FFAA66CC",
                "#FF99CC00",
                "#FFFFBB33",
                "#ff4444",
                "#FF0099CC",
                "#FFCC0000",
                "#4B0082",
                "#006400",
                "#8B0000"
        };
        String x;
        if (i < 9)
            x = clr[i++];
        else {
            i = 0;
            x = clr[i++];
        }
        return x;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.rv_dialog_two_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Assign your views here !

        //eg :- holder.tv.setText("Hi");


        holder.title.setText(data.keySet().toArray(new String[]{})[position]);
        holder.description.setText(data.values().toArray(new String[]{})[position]);


    }

    public void delete(int pos) {

        data.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;

    }

    ClickListener clickListener;

    class ViewHolder extends RecyclerView.ViewHolder {

        /*ImageView img;
        TextView title, by;
        CardView cardView;*/
        TextView title;
        TextView description;
        CardView cv;
        //Declare your views here!

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTwoRowsTitle);
            description = itemView.findViewById(R.id.tvTwoRowsDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (clickListener != null) {
                        clickListener.itemclicked(v, getPosition());
                    }
                }
            });

            //Find your views here !

            /*img = itemView.findViewById(R.id.IVSpeechRow);
            title = itemView.findViewById(R.id.TVSpeechRowTitle);
            by = itemView.findViewById(R.id.TVSpeechRowBy);
            cardView = itemView.findViewById(R.id.card1);*/

        }


    }

    public interface ClickListener {
        void itemclicked(View v, int pos);
    }
}