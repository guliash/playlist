package com.github.guliash.playlist.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.guliash.playlist.structures.Singer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * File {@link Cache} implementation
 */
public class FileCache implements Cache {

    /**
     * The cache filename
     */
    private static final String FILENAME = "singers";

    /**
     * The last update time in preferences
     */
    private static final String UPDATE_EXTRA = "update";

    /**
     * The expiration interval
     */
    private static final int EXPIRATION_INTERVAL = 60 * 1000;

    /**
     * The context
     */
    private Context mContext;

    /**
     * The serializer
     */
    private Serializer mSerializer;

    /**
     * The deserializer
     */
    private Deserializer mDeserializer;

    /**
     *
     * @param context the context
     * @param serializer the serializer 
     * @param deserializer
     */
    public FileCache(@NonNull Context context, @NonNull Serializer serializer,
                     @NonNull Deserializer deserializer) {
        mContext = context;
        mSerializer = serializer;
        mDeserializer = deserializer;
    }

    @Override
    public boolean isCached() {
        File file = new File(mContext.getCacheDir(), FILENAME);
        return file.exists();
    }

    @Override
    public boolean hasActualData() {
        return isCached() && !isExpired();
    }

    @Override
    public void cache(@NonNull List<Singer> singers) {
        write(mSerializer.serializeSingers(singers));
        putTimeToPrefs();
    }

    @Override
    public List<Singer> get() {
        String json = read();
        return mDeserializer.deserializeSingers(json);
    }

    @Override
    public boolean isExpired() {
        SharedPreferences pref = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        long prev = pref.getLong(UPDATE_EXTRA, -1);
        if(prev == -1) {
            return false;
        }
        Log.e("TAG", (System.currentTimeMillis() - prev) + "");
        return System.currentTimeMillis() - prev > EXPIRATION_INTERVAL;
    }

    /**
     * Puts last update time to the shared preferences
     */
    private void putTimeToPrefs() {
        SharedPreferences.Editor editor = mContext.
                getSharedPreferences(FILENAME, Context.MODE_PRIVATE).edit();
        editor.putLong(UPDATE_EXTRA, System.currentTimeMillis());
        editor.commit();
    }

    /**
     * Writes text to the cache file
     * @param text
     */
    private void write(String text) {
        write(new File(mContext.getCacheDir(), FILENAME), text);
    }

    /**
     * Writes text to the give file
     * @param file the file to write in
     * @param text the text to write
     */
    private void write(File file, String text) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            write(outputStream, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes text using instance of {@link OutputStream}
     * @param outputStream the instance of {@link OutputStream}
     * @param text text to write
     */
    private void write(OutputStream outputStream, String text) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        try {
            bufferedWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads string from the cached file
     * @return the read string
     */
    @Nullable private String read() {
        return read(new File(mContext.getCacheDir(), FILENAME));
    }

    /**
     * Reads string from the given file
     * @param file the file
     * @return the read string
     */
    @Nullable private String read(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            return read(inputStream);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Read string from using the given instance of {@link InputStream}
     * @param inputStream the instance of {@link InputStream}
     * @return the read string
     */
    @Nullable private String read(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String token;
        StringBuilder builder = new StringBuilder();
        try {
            while((token = reader.readLine()) != null) {
                builder.append(token);
            }
        } catch (IOException e) {
            return null;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }


}
