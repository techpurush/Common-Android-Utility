package com.techpurush.commonandroidutility.BottomsheetDialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.techpurush.commonandroidutility.R;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialogUtilsx extends BottomSheetDialogFragment {

    BSResponseSelectedInterface responseSelectedInterface;

    List<String> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet_modal, container, false);

        initViews(view);

        return view;


    }

    public void setCallback(BSResponseSelectedInterface bsResponseSelectedInterface) {

        this.responseSelectedInterface = bsResponseSelectedInterface;

    }

    public void setItems(List<String> list) {

        this.list = list;

    }

    private void initViews(View view) {

       /* List<String> list = List.of("Share with Friends",
                "Bookmark1",
                "Add to Favourites",
                "More Information"
        );*/

        ListView listView = view.findViewById(R.id.listViewOptions);

        CustomAdapter customAdapter = new CustomAdapter(getActivity(), list);

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                responseSelectedInterface.itemClicked(list.get(position));

                dismiss();

            }
        });


    }


    class CustomAdapter extends ArrayAdapter<String> {

        private Context mContext;
        private List<String> moviesList = new ArrayList<>();

        public CustomAdapter(@NonNull Context context, List<String> list) {
            super(context, 0, list);
            mContext = context;
            moviesList = list;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;
            if (listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.rv_dialog_single_item2, parent, false);


            TextView release = (TextView) listItem.findViewById(R.id.tvTwoRowsTitle);
            release.setText(moviesList.get(position));

            return listItem;
        }
    }


}
