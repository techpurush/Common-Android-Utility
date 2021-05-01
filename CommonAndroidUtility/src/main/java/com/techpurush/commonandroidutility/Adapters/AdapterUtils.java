package com.techpurush.commonandroidutility.Adapters;

import android.content.Context;

import java.util.List;
import java.util.Map;

public class AdapterUtils {


    public static SingleItemAdapter getSingleItemAdapter(List<String> list, Context context) {

        return new SingleItemAdapter(context, list);

    }

    public static TwoItemsAdapter getTwoItemsAdapter(Map<String, String> list, Context context) {

        return new TwoItemsAdapter(context, list);

    }

}
