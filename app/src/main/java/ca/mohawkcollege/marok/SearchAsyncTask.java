package ca.mohawkcollege.marok;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

public class SearchAsyncTask extends AsyncTask<String, Void, String> {

    public static final String TAG = "==SearchAsyncTask==";

    @Override
    protected String doInBackground(String... params) {
        String searchTerm = params[0];
        String type = params[1];

        String url = String.format("http://www.omdbapi.com/?s=%s&type=%s&apikey=%s", searchTerm, type,ApiHelper.API_KEY);
        Log.d(TAG, "url: " + url);
        String result = ApiHelper.jsonGet(url);
        return result;
    }

    protected void onPostExecute(String result) {
        Log.d(TAG,result);
    }

}
