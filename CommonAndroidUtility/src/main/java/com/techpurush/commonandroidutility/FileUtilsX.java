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

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class FileUtilsX {

    public static final long GB = 1073741824; // 1024 * 1024 * 1024
    public static final long MB = 1048576; // 1024 * 1024
    public static final long KB = 1024;

    public static final int ICON_TYPE_ROOT = 1;
    public static final int ICON_TYPE_FOLDER = 2;
    public static final int ICON_TYPE_MP3 = 3;
    public static final int ICON_TYPE_MTV = 4;
    public static final int ICON_TYPE_JPG = 5;
    public static final int ICON_TYPE_FILE = 6;

    public static final String MTV_REG = "^.*\\.(mp4|3gp)$";
    public static final String MP3_REG = "^.*\\.(mp3|wav)$";
    public static final String JPG_REG = "^.*\\.(gif|jpg|png)$";

    private static final String FILENAME_REGIX = "^[^\\/?\"*:<>\\]{1,255}$";

    /**
     * Don't let anyone instantiate this class.
     */
    private FileUtilsX() {
        throw new Error("Do not need instantiate!");
    }


    public static boolean deleteFile(File file) {

        return file.delete();
    }


    public static boolean DeleteFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] childFile = file.listFiles();
        if (childFile == null || childFile.length == 0) {
            return file.delete();
        }
        for (File f : childFile) {
            DeleteFile(f);
        }
        return file.delete();
    }


    public static boolean renameFile(File file, String newFileName) {
        if (newFileName.matches(FILENAME_REGIX)) {
            File newFile = null;
            if (file.isDirectory()) {
                newFile = new File(file.getParentFile(), newFileName);
            } else {
                String temp = newFileName
                        + file.getName().substring(
                        file.getName().lastIndexOf('.'));
                newFile = new File(file.getParentFile(), temp);
            }
            if (file.renameTo(newFile)) {
                return true;
            }
        }
        return false;
    }


    public static String getFileSize(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int length = fis.available();
            if (length >= GB) {
                return String.format("%.2f GB", length * 1.0 / GB);
            } else if (length >= MB) {
                return String.format("%.2f MB", length * 1.0 / MB);
            } else {
                return String.format("%.2f KB", length * 1.0 / KB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "未知";
    }


    public static void openFile(Activity activity, File file) throws Exception {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), getMimeType(file, activity));
        activity.startActivity(intent);
    }


    public static String getMimeType(File file, Activity activity)
            throws Exception {

        String name = file.getName()
                .substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
        int id = activity.getResources().getIdentifier(
                activity.getPackageName() + ":string/" + name, null, null);


        if ("class".equals(name)) {
            return "application/octet-stream";
        }
        if ("3gp".equals(name)) {
            return "video/3gpp";
        }
        if ("nokia-op-logo".equals(name)) {
            return "image/vnd.nok-oplogo-color";
        }
        if (id == 0) {
            throw new Exception("Exception in getMimeType");
        }
        return activity.getString(id);
    }


    public static List<HashMap<String, Object>> recursionFolder(File folder,
                                                                FileFilter filter) {

        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();


        File[] files = folder.listFiles();

        if (filter != null) {
            files = folder.listFiles(filter);
        }

        if (files != null) {
            for (int m = 0; m < files.length; m++) {
                File file = files[m];
                if (file.isDirectory()) {

                    list.addAll(recursionFolder(file, filter));

                } else {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("file", file);

                    if (file.getAbsolutePath().toLowerCase().matches(MP3_REG)) {
                        map.put("iconType", 3);
                    } else if (file.getAbsolutePath().toLowerCase()
                            .matches(MTV_REG)) {
                        map.put("iconType", 4);
                    } else if (file.getAbsolutePath().toLowerCase()
                            .matches(JPG_REG)) {
                        map.put("iconType", 5);
                    } else {
                        map.put("iconType", 6);
                    }
                    list.add(map);
                }
            }
        }
        return list;
    }


    public static List<HashMap<String, Object>> unrecursionFolder(File folder,
                                                                  FileFilter filter) {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        if (!folder.getAbsolutePath().equals(
                Environment.getExternalStorageDirectory().getAbsolutePath())) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("file", folder.getParentFile());
            map.put("iconType", ICON_TYPE_ROOT);
            list.add(map);
        }

        File[] files = folder.listFiles();

        if (filter != null) {
            files = folder.listFiles(filter);
        }
        if (files != null && files.length > 0) {
            for (File p : files) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("file", p);
                if (p.isDirectory()) {
                    map.put("iconType", ICON_TYPE_FOLDER);
                } else {
                    if (p.getAbsolutePath().toLowerCase().matches(MP3_REG)) {
                        map.put("iconType", ICON_TYPE_MP3);
                    } else if (p.getAbsolutePath().toLowerCase()
                            .matches(MTV_REG)) {
                        map.put("iconType", ICON_TYPE_MTV);
                    } else if (p.getAbsolutePath().toLowerCase()
                            .matches(JPG_REG)) {
                        map.put("iconType", ICON_TYPE_JPG);
                    } else {
                        map.put("iconType", ICON_TYPE_FILE);
                    }
                }

                list.add(map);
            }
        }
        return list;
    }


    public static FileFilter getFileFilter(final String reg, boolean isdir) {
        if (isdir) {
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {

                    return pathname.getAbsolutePath().toLowerCase()
                            .matches(reg)
                            || pathname.isDirectory();
                }
            };
        } else {
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {

                    return pathname.getAbsolutePath().toLowerCase()
                            .matches(reg)
                            && pathname.isFile();
                }
            };
        }
    }

    public final static String FILE_EXTENSION_SEPARATOR = "";


    public static StringBuilder readFile(String filePath, String charsetName) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (!file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(
                    file), charsetName);
            reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }


    public static boolean writeFile(String filePath, String content,
                                    boolean append) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }


    public static boolean writeFile(String filePath, List<String> contentList,
                                    boolean append) {
        if (contentList == null || contentList.size() < 1) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            int i = 0;
            for (String line : contentList) {
                if (i++ > 0) {
                    fileWriter.write("\r\n");
                }
                fileWriter.write(line);
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }


    public static boolean writeFile(String filePath, String content) {
        return writeFile(filePath, content, false);
    }


    public static boolean writeFile(String filePath, List<String> contentList) {
        return writeFile(filePath, contentList, false);
    }


    public static boolean writeFile(String filePath, InputStream stream) {
        return writeFile(filePath, stream, false);
    }


    public static boolean writeFile(String filePath, InputStream stream,
                                    boolean append) {
        return writeFile(filePath != null ? new File(filePath) : null, stream,
                append);
    }


    public static boolean writeFile(File file, InputStream stream) {
        return writeFile(file, stream, false);
    }


    public static boolean writeFile(File file, InputStream stream,
                                    boolean append) {
        OutputStream o = null;
        try {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }


    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
        return writeFile(destFilePath, inputStream);
    }


    public static final byte[] input2byte(InputStream inStream) {
        if (inStream == null)
            return null;
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        try {
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return swapStream.toByteArray();
    }


    public static List<String> readFileToList(String filePath,
                                              String charsetName) {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<String>();
        if (!file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(
                    file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }


    public static String getFileNameWithoutExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0,
                    extenPosi));
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        }
        return (filePosi < extenPosi ? filePath.substring(filePosi + 1,
                extenPosi) : filePath.substring(filePosi + 1));
    }


    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }


    public static String getFolderName(String filePath) {

        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }


    public static String getFileExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
            return "";
        }
        return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
    }


    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (TextUtils.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) || folder
                .mkdirs();
    }


    public static boolean makeFolders(String filePath) {
        return makeDirs(filePath);
    }


    public static boolean isFileExist(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }


    public static boolean isFolderExist(String directoryPath) {
        if (TextUtils.isEmpty(directoryPath)) {
            return false;
        }

        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }


    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }


    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return -1;
        }

        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }


    public static File uri2File(Activity activity, Uri uri) {
        if (Build.VERSION.SDK_INT < 11) {
            // 在API11以下可以使用：managedQuery
            String[] proj = {MediaStore.Images.Media.DATA};
            @SuppressWarnings("deprecation")
            Cursor actualimagecursor = activity.managedQuery(uri, proj, null, null,
                    null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor
                    .getString(actual_image_column_index);
            return new File(img_path);
        } else {
            // 在API11以上：要转为使用CursorLoader,并使用loadInBackground来返回
            String[] projection = {MediaStore.Images.Media.DATA};
            CursorLoader loader = new CursorLoader(activity, uri, projection, null,
                    null, null);
            Cursor cursor = loader.loadInBackground();
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return new File(cursor.getString(column_index));
        }
    }

}
