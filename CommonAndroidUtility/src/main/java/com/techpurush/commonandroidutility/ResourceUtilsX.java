/**
 * Copyright 2014 Zhenguo Jin
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techpurush.commonandroidutility;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class ResourceUtilsX {

    /**
     * Don't let anyone instantiate this class.
     */
    private ResourceUtilsX() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * get an asset using ACCESS_STREAMING mode. This provides access to files that have been bundled with an
     * application as assets -- that is, files placed in to the "assets" directory.
     *
     * @param context
     * @param fileName The name of the asset to open. This name can be hierarchical.
     * @return
     */
    public static String geFileFromAssets(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get content from a raw resource. This can only be used with resources whose value is the name of an asset files
     * -- that is, it can be used to open drawable, sound, and raw resources; it will fail on string and color
     * resources.
     *
     * @param context
     * @param resId   The resource identifier to open, as generated by the appt tool.
     * @return
     */
    public static String geFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * same to {@link ResourceUtilsX#geFileFromAssets(Context, String)}, but return type is List<String>
     *
     * @param context
     * @param fileName
     * @return
     */
    public static List<String> geFileToListFromAssets(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        List<String> fileContent = new ArrayList<String>();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            br.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * same to {@link ResourceUtilsX#geFileFromRaw(Context, int)}, but return type is List<String>
     *
     * @param context
     * @param resId
     * @return
     */
    public static List<String> geFileToListFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        List<String> fileContent = new ArrayList<String>();
        BufferedReader reader;
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
