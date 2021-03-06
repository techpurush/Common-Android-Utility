/*
 * Copyright 2019 Pranav Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techpurush.commonandroidutility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Helper class to perform various file operations.
 *
 * <p><p>A {@link FileProvider} in the form of {@code ${applicationId}.FileProvider} must be
 * added in the {@code manifest} to perform some operations automatically like saving the
 * bitmap or file in app isolated directory.
 *
 * @see Context#getExternalFilesDir(String)
 */
public class FileUtils {

    /**
     * Default suffix for the file provider.
     */
    private static final String FILE_PROVIDER = ".FileProvider";

    /**
     * Constant to match the content uri.
     */
    private static final String URI_MATCHER_CONTENT = "content:";

    /**
     * Constant to match the file uri.
     */
    private static final String URI_MATCHER_FILE = "file:";

    /**
     * Constant for the default data directory.
     */
    public static final String DEFAULT_DIR_DATA = "data";

    /**
     * Constant for the default temp directory.
     */
    public static final String DEFAULT_DIR_TEMP = "temp";

    /**
     * Constant for the {@code application/octet-stream} mime type.
     */
    public static final String MIME_OCTET_STREAM = "application/octet-stream";

    /**
     * Returns the default {@code temp} directory for a context.
     *
     * @param context The context to get the package name.
     */
    public static @Nullable String getTempDir(@NonNull Context context) {
        if (context.getExternalFilesDir(null) == null) {
            return null;
        }

        return context.getExternalFilesDir(null).getPath()
                + File.separator + DEFAULT_DIR_DATA
                + File.separator + context.getPackageName()
                + File.separator + DEFAULT_DIR_TEMP + File.separator;
    }

    /**
     * Returns the base name without extension of given file name.
     * <p>e.g. getBaseName("file.txt") will return "file".
     *
     * @param fileName The full name of the file with extension.
     *
     * @return The base name of the file without extension.
     */
    public static @Nullable String getBaseName(@Nullable String fileName) {
        if (fileName == null) {
            return null;
        }

        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return fileName;
        } else {
            return fileName.substring(0, index);
        }
    }

    /**
     * Returns the extension of a file name.
     *
     * @param fileName The file name to retrieve its extension.
     *
     * @return The extension of the file name.
     */
    public static @Nullable String getExtension(@Nullable String fileName) {
        if (fileName == null) {
            return null;
        }

        String extension = null;
        int i = fileName.lastIndexOf('.');

        if (i > 0 && i < fileName.length() - 1) {
            extension = fileName.substring(i + 1).toLowerCase();
        }

        return extension;
    }

    /**
     * Returns the extension of a file.
     *
     * @param file The file to retrieve its extension.
     *
     * @return The extension of the file.
     */
    public static @Nullable String getExtension(@Nullable File file) {
        if (file == null) {
            return null;
        }

        return getExtension(file.getName());
    }

    /**
     * Verifies a file if it exist or not.
     *
     * @param file The file to be verified.
     *
     * @return {@code true} if a file can be accessed by automatically creating the
     *         sub directories.
     */
    public static boolean verifyFile(@Nullable File file) {
        if (file == null) {
            return false;
        }

        boolean fileExists = file.exists();
        if (!fileExists) {
            fileExists = file.mkdirs();
        }

        return fileExists;
    }

    /**
     * Delete a directory.
     *
     * @param dir The directory to be deleted.
     *
     * @return {@code true} if the directory has been deleted successfully.
     */
    public static boolean deleteDirectory(@NonNull File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();

            if (children != null) {
                for (String aChildren : children) {
                    boolean success = deleteDirectory(new File(dir, aChildren));
                    if (!success) {
                        return false;
                    }
                }
            }
        }

        return dir.delete();
    }

    /**
     * Creates a zip archive from the directory.
     *
     * @param dir The directory to be archived.
     * @param zip The output zip archive.
     *
     * @throws IOException Throws IO exception.
     */
    public static void zipDirectory(@NonNull File dir, @NonNull File zip) throws IOException {
        if (zip.getParent() != null && verifyFile(new File(zip.getParent()))) {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
            zip(dir, dir, zos);
            zos.close();
        }
    }

    /**
     * Creates a zip archive from the zip output stream.
     *
     * @param dir The directory to be archived.
     * @param zip The output zip archive.
     * @param zos The zip output stream.
     */
    private static void zip(@NonNull File dir, @NonNull File zip,
            @NonNull ZipOutputStream zos) throws IOException {
        File[] files = dir.listFiles();
        byte[] buffer = new byte[8192];
        int read;

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    zip(file, zip, zos);
                } else {
                    FileInputStream in = new FileInputStream(file);
                    ZipEntry entry = new ZipEntry(file.getPath().substring(
                            zip.getPath().length() + 1));
                    zos.putNextEntry(entry);

                    while (-1 != (read = in.read(buffer))) {
                        zos.write(buffer, 0, read);
                    }

                    in.close();
                }
            }
        }
    }

    /**
     * Extracts a zip archive.
     *
     * @param zip The zip archive to be extracted.
     * @param extractTo The unzip destination.
     *
     * @throws IOException Throws IO exception.
     * @throws ZipException Throws Zip exception.
     */
    public static void unzip(@NonNull File zip, @NonNull File extractTo)
            throws SecurityException, IOException {
        ZipFile archive = new ZipFile(zip);
        Enumeration e = archive.entries();

        while (e.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) e.nextElement();
            File file = new File(extractTo, entry.getName());

            if (!file.getCanonicalPath().startsWith(extractTo.getCanonicalPath())) {
                throw new SecurityException("Unsafe unzipping pattern that may " +
                        "lead to a Path Traversal vulnerability.");
            } else {
                if (entry.isDirectory() && !file.exists()) {
                    verifyFile(file);
                } else {
                    if (verifyFile(file.getParentFile())) {
                        InputStream in = archive.getInputStream(entry);
                        BufferedOutputStream out =
                                new BufferedOutputStream(new FileOutputStream(file));

                        byte[] buffer = new byte[8192];
                        int read;

                        while (-1 != (read = in.read(buffer))) {
                            out.write(buffer, 0, read);
                        }

                        in.close();
                        out.close();
                    }
                }
            }
        }
    }

    /**
     * Returns uri from the file. 
     * <p>It will automatically use the @link FileProvider} on API 24 and above devices.
     *
     * @param context The context to get the file provider.
     * @param file The file to get the uri.
     *
     * @return The uri from the file.
     *
     * @see Uri
     */
    public static @Nullable Uri getUriFromFile(@NonNull Context context, @Nullable File file) {
        if (file == null) {
            return null;
        }

        if (SdkUtils.is23()) {
            return FileProvider.getUriForFile(context.getApplicationContext(),
                    context.getPackageName() + FILE_PROVIDER, file);
        } else {
            return (Uri.fromFile(file));
        }
    }

    /**
     * Returns file name from the uri.
     *
     * @param context The context to get content resolver.
     * @param uri The uri to get the file name.
     *
     * @return The file name from the uri.
     *
     * @see Context#getContentResolver()
     */
    public @Nullable static String getFileNameFromUri(
            @NonNull Context context, @Nullable Uri uri) {
        if (uri == null) {
            return null;
        }

        String fileName = null;
        Cursor cursor = null;

        if (uri.toString().contains(URI_MATCHER_CONTENT)) {
            try {
                cursor = context.getContentResolver().query(uri, null,
                        null, null, null);

                if (cursor != null) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    cursor.moveToFirst();
                    fileName = cursor.getString(nameIndex);
                }
            } catch (Exception ignored) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } else if (uri.toString().contains(URI_MATCHER_FILE)) {
            try {
                fileName = new File(new URI(uri.toString())).getName();
            } catch (Exception ignored) {
            }
        }

        return fileName;
    }

    /**
     * Writes a file from the source to destination.
     *
     * @param source The source file.
     * @param destination The destination file.
     * @param outputFileName The output files name.
     *
     * @return {@code true} if the file has been written successfully.
     */
    public static boolean writeToFile(@NonNull File source, 
            @NonNull File destination, @NonNull String outputFileName) {
        boolean success = false;

        try {
            if (FileUtils.verifyFile(destination)) {
                FileInputStream input = new FileInputStream(source);
                OutputStream output = new FileOutputStream(destination
                        + File.separator + outputFileName);
                byte[] buffer = new byte[1024];
                int length;

                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                output.flush();
                output.close();
                input.close();

                success = true;
            }
        } catch (Exception ignored) {
        }

        return success;
    }

    /**
     * Writes a file uri from the source to destination.
     *
     * @param context The context to get content resolver.
     * @param sourceUri The source file uri.
     * @param destinationUri The destination file uri.
     *
     * @return {@code true} if the file has been written successfully.
     */
    public static boolean writeToFile(@NonNull Context context,
            @Nullable Uri sourceUri, @Nullable Uri destinationUri) {
        if (sourceUri == null || destinationUri == null) {
            return false;
        }

        boolean success = false;

        try {
            InputStream input = context.getContentResolver().openInputStream(sourceUri);
            ParcelFileDescriptor pfdDestination = context.getContentResolver().
                    openFileDescriptor(destinationUri, "w");

            if (input != null && pfdDestination != null) {
                OutputStream output = new FileOutputStream(pfdDestination.getFileDescriptor());
                byte[] buffer = new byte[1024];
                int length;

                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                output.flush();
                output.close();
                input.close();
                pfdDestination.close();

                success = true;
            }
        } catch (Exception ignored) {
        }

        return success;
    }

    /**
     * Writes a string data to file uri from the source to destination.
     *
     * @param context The context to get content resolver.
     * @param data The string data to be written.
     * @param sourceUri The source file uri.
     * @param destinationUri The destination file uri.
     *
     * @return {@code true} if the file has been written successfully.
     */
    public static boolean writeStringToFile(@NonNull Context context,
            @Nullable String data, @Nullable Uri sourceUri, @Nullable Uri destinationUri) {
        if (sourceUri == null) {
            return false;
        }

        boolean success = false;

        try {
            ParcelFileDescriptor pfdDestination = context.getContentResolver().
                    openFileDescriptor(sourceUri, "w");

            if (pfdDestination != null) {
                OutputStream output = new FileOutputStream(pfdDestination.getFileDescriptor());

                if (data != null) {
                    output.write(data.getBytes());
                }

                output.flush();
                output.close();
                pfdDestination.close();
            }

            if (destinationUri != null) {
                success = writeToFile(context, sourceUri, destinationUri);
            } else {
                success = true;
            }
        } catch (Exception ignored) {
        }

        return success;
    }

    /**
     * Writes a string data to file uri.
     *
     * @param context The context to get content resolver.
     * @param data The string data to be written.
     * @param sourceUri The source file uri.
     *
     * @return {@code true} if the file has been written successfully.
     */
    public static boolean writeStringToFile(@NonNull Context context,
            @Nullable String data, @Nullable Uri sourceUri) {
        return writeStringToFile(context, data, sourceUri, null);
    }

    /**
     * Reads a string data from the file uri.
     *
     * @param context The context to get content resolver.
     * @param fileUri The source file uri.
     *
     * @return The string data after reading the file.
     */
    public static @Nullable String readStringFromFile(
            @NonNull Context context, @NonNull Uri fileUri) {
        try {
            InputStream input = context.getContentResolver().openInputStream(fileUri);

            if (input != null) {
                InputStreamReader inputReader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(inputReader);
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                input.close();
                inputReader.close();
                bufferedReader.close();

                return stringBuilder.toString();
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    /**
     * Save and returns uri from the bitmap.
     * <p>It will automatically use the @link FileProvider} on API 24 and above devices.
     *
     * <p<p>It requires {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} permission on
     * pre KitKat ({@link android.os.Build.VERSION_CODES#JELLY_BEAN_MR2} or below) devices.
     *
     * @param context The context to get the file provider.
     * @param bitmap The bitmap to get the uri.
     *
     * @return The uri from the bitmap.
     *
     * @see Uri
     */
    public static @Nullable Uri getBitmapUri(@NonNull Context context,
            @Nullable Bitmap bitmap, @NonNull String name) {
        Uri bitmapUri = null;

        if (bitmap != null) {
            try {
                File picturesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                if (picturesDir != null) {
                    File storagePath = new File(picturesDir.getPath(), name);
                    String image = storagePath + File.separator + name + ".png";
                    storagePath.mkdirs();

                    FileOutputStream out = new FileOutputStream(image);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.close();
                    bitmapUri = getUriFromFile(context, new File(image));
                }
            } catch (Exception ignored) {
            }
        }

        return bitmapUri;
    }

    /**
     * Checks whether the extension is valid for a path.
     *
     * @param path The path string to get the extension.
     * @param extension The extension to be validated.
     *
     * @return {@code true} if the extension is valid for the path.
     */
    public static boolean isValidExtension(@Nullable String path, @Nullable String extension) {
        if (path == null || extension == null) {
            return false;
        }

        return extension.equals("." + getExtension(path));
    }

    /**
     * Checks whether the mime type is valid for an intent data.
     *
     * @param context The context to match the uri mime type.
     * @param intent The intent to get the data.
     * @param mimeType The mime type to be validated.
     * @param extension The optional extension to be validated if mime type is invalid.
     *
     * @return {@code true} if the mime type is valid for the intent data.
     */
    public static boolean isValidMimeType(@Nullable Context context,
            @Nullable Intent intent, @NonNull String mimeType, @Nullable String extension) {
        if (intent == null || intent.getAction() == null || intent.getData() == null) {
            return false;
        }

        boolean validMime = mimeType.equals(intent.getType());

        if (!validMime) {
            validMime = isValidMimeType(context, intent.getData(), MIME_OCTET_STREAM, extension)
                    && isValidExtension(getFileNameFromUri(context, intent.getData()), extension);
        }

        return validMime;
    }

    /**
     * Checks whether the mime type is valid for a uri.
     *
     * @param context The context to get the content resolver.
     * @param uri The uri to get the type.
     * @param mimeType The mime type to be validated.
     * @param extension The optional extension to be validated if mime type is invalid.
     *
     * @return {@code true} if the mime type is valid for the intent data.
     */
    public static boolean isValidMimeType(@Nullable Context context,
            @Nullable Uri uri, @NonNull String mimeType, @Nullable String extension) {
        if (context == null || uri == null) {
            return false;
        }

        boolean validMime;

        String type = context.getApplicationContext().getContentResolver().getType(uri);
        validMime = mimeType.equals(type);


        if (!validMime) {
            validMime = MIME_OCTET_STREAM.equals(type)
                    && isValidExtension(getFileNameFromUri(context, uri), extension);
        }

        return validMime;
    }

    /**
     * Checks whether the mime type is valid for a file.
     *
     * @param context The context to get the content resolver.
     * @param file The file to get the uri.
     * @param mimeType The mime type to be validated.
     * @param extension The optional extension to be validated if mime type is invalid.
     *
     * @return {@code true} if the mime type is valid for the intent data.
     */
    public static boolean isValidMimeType(@NonNull Context context,
            @Nullable File file, @NonNull String mimeType, @Nullable String extension) {
        return isValidMimeType(context, getUriFromFile(context, file), mimeType, extension);
    }

    /**
     * Share file according to the mime type.
     *
     * @param activity The activity to create the intent chooser.
     * @param title The title for the intent chooser.
     * @param subject The subject for the intent chooser.
     * @param file The file to be shared.
     * @param mimeType The mime type of the file.
     */
    public static void shareFile(@NonNull Activity activity, @Nullable String title,
            @Nullable String subject, @NonNull File file, @NonNull String mimeType) {
        Intent shareBackup = ShareCompat.IntentBuilder
                .from(activity)
                .setType(mimeType)
                .setSubject(subject != null ? subject : title)
                .setStream(getUriFromFile(activity, file))
                .setChooserTitle(title)
                .createChooserIntent()
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        activity.startActivity(shareBackup);
    }

    /**
     * Share multiple files.
     *
     * @param activity The activity to create the intent chooser.
     * @param title The title for the intent chooser.
     * @param subject The subject for the intent chooser.
     * @param uris The content uris to be shared.
     * @param mimeType The mime type of the file.
     */
    public static void shareFiles(@NonNull Activity activity, @Nullable String title,
            @Nullable String subject, @NonNull Uri[] uris, @Nullable String mimeType) {
        ShareCompat.IntentBuilder intentBuilder =
                ShareCompat.IntentBuilder
                        .from(activity)
                        .setSubject(subject != null ? subject : title)
                        .setType(mimeType != null ? mimeType : "*/*")
                        .setChooserTitle(title);

        for (Uri uri : uris) {
            intentBuilder.addStream(uri);
        }

        Intent shareBackup = intentBuilder.createChooserIntent()
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        activity.startActivity(shareBackup);
    }

    /**
     * Returns an intent to request a storage location for the supplied file.
     *
     * @param context The context to get the file uri.
     * @param file The file to request the storage location.
     * @param mimeType The mime type of the file.
     *
     * @return The intent to request a storage location for the supplied file.
     */
    public static Intent getSaveToFileIntent(@NonNull Context context,
            @NonNull File file, @NonNull String mimeType) {
        Uri uri = getUriFromFile(context, file);
        Intent intent;

        if (SdkUtils.is19()) {
            intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        } else {
            intent = new Intent(Intent.ACTION_PICK);
        }

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_TITLE, file.getName());
        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        return intent;
    }

    /**
     * Returns an intent to select a file according to the mime type.
     *
     * @param mimeType The mime type for the file.
     *
     * @return The intent intent to select a file according to the mime type.
     */
    public static Intent getFileSelectIntent(@NonNull String mimeType) {
        Intent intent;

        if (SdkUtils.is19()) {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("*/*");
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType(mimeType);
        }

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        return intent;
    }
}
