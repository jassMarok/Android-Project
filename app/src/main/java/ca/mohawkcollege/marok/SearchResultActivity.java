package ca.mohawkcollege.marok;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SearchResultActivity extends AppCompatActivity {

    private static String TAG = "==MainActivity==";
    private static SearchResultActivity _activity;

    public static SearchResultActivity getActivity() {
        return _activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Log.d(TAG, "onCreate");

        _activity = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String searchTerm = extras.getString(MainActivity.Intent_Search_Term);
            String searchType = extras.getString(MainActivity.Intent_Search_Type);
            //The key argument here must match that used in the other activity
            Log.d(TAG, "dispatching request for data");
            new SearchAsyncTask().execute(searchTerm, searchType);
        }
    }
}