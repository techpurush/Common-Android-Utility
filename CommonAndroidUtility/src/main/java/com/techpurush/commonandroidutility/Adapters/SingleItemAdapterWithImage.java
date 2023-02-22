package com.techpurush.commonandroidutility.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.techpurush.commonandroidutility.R;

import java.util.List;


public class SingleItemAdapterWithImage extends RecyclerView.Adapter<SingleItemAdapterWithImage.ViewHolder> {
    Context context;
    List<String> data;

    public SingleItemAdapterWithImage(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.rv_dialog_single_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Assign your views here !

        //eg :- holder.tv.setText("Hi");
        holder.tv.setText(data.get(position).toString());
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
        TextView tv;
        CardView cv;
        //Declare your views here!

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvTwoRowsTitle);
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