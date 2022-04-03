package ca.mohawkcollege.marok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class DetailsActivity extends AppCompatActivity {

    private static String TAG = "==DetailsActivity==";
    public static final String IMDB_ID_KEY = "ImdbID";

    private static DetailsActivity _activity;

    public static DetailsActivity getActivity() {
        return _activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Log.d(TAG, "onCreate");

        _activity = this;

        // Load other information about title via imdb call
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.d(TAG, "Details intent data not found");
            return;
        }

        String imdbId = extras.getString(DetailsActivity.IMDB_ID_KEY);

        Log.d(TAG, "dispatching request for data");
        Log.d(TAG, "IMDB ID: " + imdbId);

        new DetailsAsyncTask().execute(imdbId);

    }
}