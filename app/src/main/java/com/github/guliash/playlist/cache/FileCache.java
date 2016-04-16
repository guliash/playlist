package com.github.guliash.playlist.cache;

import android.content.Context;
import android.content.SharedPreferences;
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
 * Created by gulash on 11.04.16.
 */
public class FileCache implements Cache {

    private static final String FILENAME = "singers";
    private static final String UPDATE_EXTRA = "update";
    private static final int EXPIRATION_INTERVAL = 1 * 60 * 1000;

    private Context mContext;
    private Serializer mSerializer;
    private Deserializer mDeserializer;

    public FileCache(Context context, Serializer serializer, Deserializer deserializer) {
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
    public void cache(List<Singer> singers) {
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

    private void putTimeToPrefs() {
        SharedPreferences.Editor editor = mContext.
                getSharedPreferences(FILENAME, Context.MODE_PRIVATE).edit();
        editor.putLong(UPDATE_EXTRA, System.currentTimeMillis());
        editor.commit();
    }

    private void write(String text) {
        write(new File(mContext.getCacheDir(), FILENAME), text);
    }

    private void write(File file, String text) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            write(outputStream, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private String read() {
        return read(new File(mContext.getCacheDir(), FILENAME));
    }

    private String read(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            return read(inputStream);
        } catch (IOException e) {
            return null;
        }
    }

    private String read(InputStream inputStream) {
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
