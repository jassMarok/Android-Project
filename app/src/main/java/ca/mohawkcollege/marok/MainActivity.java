package ca.mohawkcollege.marok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "==MainActivity==";

    private MainActivity _mainActivity;
    private EditText _editText;
    private RadioGroup _radioGroupSearchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");
        _mainActivity = this;
        _editText = findViewById(R.id.editTextSearchTerm);
        _radioGroupSearchType = findViewById(R.id.radioGroupSearchType);
    }

    public void onSearchClick(View view) {
        // Check the Search term -> Cannot be empty
        Log.d(TAG, "onSearchClick");
        String searchTerm = _editText.getText().toString();
        searchTerm = searchTerm.trim();
        if (searchTerm.equals("")) {
            Toast.makeText(getApplicationContext(), "Search term cannot be empty", Toast.LENGTH_SHORT)
                    .show();
            Log.d(TAG, "empty search term");
            return;
        }

        // Selected radio
        String searchType = "movie";
        int checkedRadioButtonId = _radioGroupSearchType.getCheckedRadioButtonId();
        switch (checkedRadioButtonId){
            case R.id.radioButton2:
              searchType = "series";
              break;
            default:
                break;
        }

        // Request Data
        Log.d(TAG, "searchTerm: " + searchTerm);
        Log.d(TAG, "searchType: " + searchType);
        Log.d(TAG, "dispatching request for data");
        new SearchAsyncTask().execute(searchTerm, searchType);
    }
}