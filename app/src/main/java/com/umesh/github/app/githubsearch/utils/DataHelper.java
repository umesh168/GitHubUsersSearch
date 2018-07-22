package com.umesh.github.app.githubsearch.utils;

import android.content.res.AssetManager;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.github.app.githubsearch.GitHubApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataHelper {

    private GitHubApp ChariotApp;
    public static String BASE_FOLDER_PATH = File.separatorChar + "CHARIOT" + File.separatorChar;
    public static String PICTURE_FOLDER_PATH = BASE_FOLDER_PATH + "pics" + File.separatorChar;

    public DataHelper(GitHubApp ChariotApp) {
        this.ChariotApp = ChariotApp;
    }

    public Map<String, Object> readJsonFileFromAssets(String fileName) {
        ChariotApp.getLogger().debug("Reading JSON");
        AssetManager assetManager = ChariotApp.getAssets();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            map = objectMapper.readValue(assetManager.open(fileName), Map.class);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static boolean isExtStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static void createDataDirIfNotExists() {
        if (isExtStorageAvailable()) {
            File root = Environment.getExternalStorageDirectory();
            String path = root.getAbsolutePath() + PICTURE_FOLDER_PATH;
            File picsDirectory = new File(path);
            if (!(picsDirectory.exists() && picsDirectory.isDirectory())) {
                picsDirectory.mkdirs();
            }
        }
    }

    public static String getPictureDirectory() {
        File root = Environment.getExternalStorageDirectory();
        String path = root.getAbsolutePath() + PICTURE_FOLDER_PATH;
        return path;
    }

    public static String getMimeType(File file) {
        String mimeType = null;
        try {
            String extension = MimeTypeMap.getFileExtensionFromUrl(file.getAbsolutePath().replaceAll("[(,), ,_]", ""));
            if (null != extension) {
                if (extension.equals("JPG")) {
                    extension = "jpg";
                    mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                } else {
                    mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mimeType;
    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}
