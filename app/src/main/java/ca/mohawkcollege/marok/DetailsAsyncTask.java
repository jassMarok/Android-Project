package ca.mohawkcollege.marok;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class DetailsAsyncTask extends AsyncTask<Object, Void, String> {
    public static final String TAG = "==SearchAsyncTask==";
    public static final String ERROR = "Request error";

    @Override
    protected String doInBackground(Object... params) {
        String imdbId = (String) params[0];

        String url = String.format("http://www.omdbapi.com/?i=%s&apikey=%s", imdbId,ApiHelper.API_KEY);
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

        Activity currentActivity = DetailsActivity.getActivity();

        if(result.equals(ERROR)){
            Log.d(TAG,"Error: " + result);
            Toast.makeText(currentActivity.getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT)
                    .show();
        }

        Log.d(TAG, "Results: "  + result);

        Gson gson = new Gson();
        DetailResult detailResult = gson.fromJson(result, DetailResult.class);

        TextView title = currentActivity.findViewById(R.id.textViewDetailsTitle);
        title.setText(detailResult.Title);

        TextView actors = currentActivity.findViewById(R.id.textViewActors);
        actors.setText(detailResult.Actors);

        TextView rated = currentActivity.findViewById(R.id.textViewDetailsRated);
        rated.setText(detailResult.Rated);

        TextView imdbRating = currentActivity.findViewById(R.id.textViewIMDBRating);
        imdbRating.setText(detailResult.imdbRating);

        TextView plot = currentActivity.findViewById(R.id.textViewDetailsPlot);
        plot.setText(detailResult.Plot);

        new LoadImageAsyncTask().execute(detailResult.Poster, currentActivity.findViewById(R.id.imageViewPoster));
    }
}
