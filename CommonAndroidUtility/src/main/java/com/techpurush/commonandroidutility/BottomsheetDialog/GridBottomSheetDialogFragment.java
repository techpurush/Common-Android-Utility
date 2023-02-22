package com.techpurush.commonandroidutility.BottomsheetDialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.techpurush.commonandroidutility.R;

import java.util.List;

public class GridBottomSheetDialogFragment extends BottomSheetDialogFragment {
    GridBottomSheetDialogListener gridBottomSheetDialogListener;

    String[] titles;
    Bitmap[] bitmaps;
    Context context;

    public interface GridBottomSheetDialogListener {
        void onButtonClicked(String item, int position);
    }

    public enum TypeAction {
        NOTES,
        MESSENGER,
        MAIL,
        DOCS,
        FILES
    }

    public GridBottomSheetDialogFragment(Context context, String[] titles, Bitmap[] bitmaps, GridBottomSheetDialogListener gridBottomSheetDialogListener) {
        this.titles = titles;
        this.bitmaps = bitmaps;
        this.gridBottomSheetDialogListener = gridBottomSheetDialogListener;
        this.context = context;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_grid_rv, viewGroup, false);

        initRV(inflate, titles, bitmaps, gridBottomSheetDialogListener);

        //setListeners(inflate);

        return inflate;
    }


    private void initRV(View inflate, String[] titles, Bitmap[] bitmaps, GridBottomSheetDialogListener gridBottomSheetDialogListener) {

        RecyclerView rv = inflate.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new GridLayoutManager(context, 3));

        GridAdapter gridAdapter = new GridAdapter(context, titles, bitmaps);

        rv.setAdapter(gridAdapter);

        gridAdapter.setItemClickListener(new ClickListener() {
            @Override
            public void itemclicked(String item, int position) {

                gridBottomSheetDialogListener.onButtonClicked(item, position);

                dismiss();

            }
        });

    }

    public interface ClickListener {
        void itemclicked(String item, int position);
    }


    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
        Context context;
        String[] titles;
        Bitmap[] bitmaps;

        public GridAdapter(Context context, String[] titles, Bitmap[] bitmaps) {
            this.context = context;
            this.titles = titles;
            this.bitmaps = bitmaps;
        }

        public void setItemClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;

        }

        ClickListener clickListener;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_grid2, parent, false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //Assign your views here !

            //eg :- holder.tv.setText("Hi");
            //holder.tv.setText(data.get(position).toString());

            holder.title.setText(titles[position]);
            holder.img.setImageBitmap(bitmaps[position]);

        }


        @Override
        public int getItemCount() {
            return titles.length;
        }


        class ViewHolder extends RecyclerView.ViewHolder {

            /*ImageView img;
            TextView title, by;
            CardView cardView;*/
            //TextView tv;
            AppCompatImageView img;
            TextView title;
            //Declare your views here!

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                img = itemView.findViewById(R.id.btnNotes);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (clickListener != null) {

                            clickListener.itemclicked(titles[getPosition()], getPosition());

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


    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }


   /* private void setListeners(View view) {
        view.findViewById(R.id.btnNotes).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                GridBottomSheetDialogFragment.this.listener.onButtonClicked("btnNotes");
                dismiss();
            }
        });
        view.findViewById(R.id.btnMessenger).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                GridBottomSheetDialogFragment.this.listener.onButtonClicked("btnMessenger");
                dismiss();

            }
        });
        view.findViewById(R.id.btnMail).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                GridBottomSheetDialogFragment.this.listener.onButtonClicked("btnMail");
                dismiss();

            }
        });
        view.findViewById(R.id.btnDocs).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                GridBottomSheetDialogFragment.this.listener.onButtonClicked("btnDocs");
                dismiss();

            }
        });
        view.findViewById(R.id.btnFiles).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                GridBottomSheetDialogFragment.this.listener.onButtonClicked("btnFiles");
                dismiss();

            }
        });
    }
*/
}
