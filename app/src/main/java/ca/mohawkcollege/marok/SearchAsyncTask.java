package ca.mohawkcollege.marok;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.URL;

public class SearchAsyncTask extends AsyncTask<String, Void, String> {

    public static final String TAG = "==SearchAsyncTask==";
    public static final String ERROR = "Request error";

    @Override
    protected String doInBackground(String... params) {
        String searchTerm = params[0];
        String type = params[1];

        String url = String.format("http://www.omdbapi.com/?s=%s&type=%s&apikey=%s", searchTerm, type,ApiHelper.API_KEY);
        Log.d(TAG, "url: " + url);
        String result = null;
        try {
            result = ApiHelper.jsonGet(url);
        } catch (Exception e) {
           return ERROR;
        }
        return result;
    }

    protected void onPostExecute(String result) {
        Log.d(TAG,"onPostExecute");

        if (result == null) {
            Log.d(TAG, "no results");
        }

        Activity currentActivity = SearchResultActivity.getActivity();

        if(result.equals(ERROR)){
            Log.d(TAG,"Error: " + result);
            Toast.makeText(currentActivity.getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT)
                    .show();
        }

        Log.d(TAG, "Results: "  + result);

        Gson gson = new Gson();
        SearchResult searchResult = gson.fromJson(result, SearchResult.class);

        // Set the array adapter
        ListAdapter listAdapter = new ListAdapter(currentActivity.getApplicationContext(), searchResult.Search);
        ListView lv = currentActivity.findViewById(R.id.listviewResults);
        lv.setAdapter(listAdapter);
    }

}
