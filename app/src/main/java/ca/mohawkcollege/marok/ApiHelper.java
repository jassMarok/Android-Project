package ca.mohawkcollege.marok;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiHelper {
    private static final String TAG = "==ApiHelper==";

    public static final String API_KEY = "7709ed90";

    public static String jsonGet(String link) throws Exception {
        StringBuilder results = new StringBuilder();
        try {
            URL url = new URL(link);
            String line = null;
            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();
            int statusCode = conn.getResponseCode();
            if (statusCode == 200) {
                InputStream inputStream = new BufferedInputStream(
                        conn.getInputStream());
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream,
                                "UTF-8"));
                while ((line = bufferedReader.readLine()) != null) {
                    results.append(line);
                }
            }

            Log.d(TAG, "Data received = " + results.length());
            Log.d(TAG, "Response Code: " + statusCode);

        } catch (MalformedURLException ex) {
            Log.d(TAG, "Malformed URL Exception: " + ex);
            throw new Exception("URL not valid");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Caught IO Exception: " + e);
            throw new Exception("Request error");
        }

        return results.toString();
    }
}
