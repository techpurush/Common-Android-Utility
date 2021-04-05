package com.techpurush.commonandroidutility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.techpurush.commonandroidutility.adapters.RVAdapter;

import java.util.List;

public class DialogUtils {

    public static void alert(@Nullable Context context, Object msg) {

        new AlertDialog.Builder(context).setMessage(msg + "").show();
    }

    public static void tst(Context context, Object msg) {
        Toast.makeText(context, msg + "", Toast.LENGTH_SHORT).show();
    }

    public static void showSnackBar(ViewGroup rootLayout, String msg) {

        Snackbar snackbar = Snackbar.make(rootLayout, msg, Snackbar.LENGTH_SHORT);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (snackbar != null)
                    snackbar.dismiss();

            }
        }).show();
    }

    public static void showDialog(Context context, String msg) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_layout1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tv = dialog.findViewById(R.id.tvMSG);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);

        tv.setText(msg);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dialog != null)
                    dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static void showDialogList(Context context, List<String> data) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_list);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RecyclerView rv = dialog.findViewById(R.id.rvDialog);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);

        rv.setLayoutManager(new LinearLayoutManager(context));

        rv.setAdapter(new RVAdapter(context, data));


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dialog != null)
                    dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


}
