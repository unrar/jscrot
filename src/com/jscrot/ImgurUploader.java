package com.jscrot;

/**
 * Created by Tomeu on 27/12/2014.
 */
 import java.io.*;
 import java.net.*;


class ImgurUploader {
    public static String uploadImage(String clientID, String dataImage) throws Exception {
        // Variable declaration
        URL url;
        url = new URL("https://api.imgur.com/3/image"); // imgur API v3
        // Define the connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // Encode the URL
        String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(dataImage, "UTF-8");

        // Connect to the API
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Client-ID " + clientID);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.connect();

        // I/O
        StringBuilder stb = new StringBuilder();
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append("\n");
        }
        wr.close();
        rd.close();

        return stb.toString();
    }

}