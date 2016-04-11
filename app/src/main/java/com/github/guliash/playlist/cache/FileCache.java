package com.github.guliash.playlist.cache;

import android.content.Context;

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

    private Context mContext;

    public FileCache(Context context) {
        mContext = context;
    }

    @Override
    public boolean isCached() {
        File file = new File(mContext.getCacheDir(), FILENAME);
        return file.exists();
    }

    @Override
    public void cache(List<Singer> singers, Serializer serializer) {
        write(serializer.serializeSingers(singers));
    }

    @Override
    public List<Singer> get(Deserializer deserializer) {
        String json = read();
        return deserializer.deserializeSingers(json);
    }

    @Override
    public boolean isExpired() {
        return false;
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
