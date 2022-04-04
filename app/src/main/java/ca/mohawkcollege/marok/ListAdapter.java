package ca.mohawkcollege.marok;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<SearchItem> {
    public static final String TAG = "==ListAdapter==";

    private Context _context;

    public ListAdapter(Context context, ArrayList<SearchItem> searchItems) {
        super(context, R.layout.search_result_list_item, searchItems);
        _context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG, "Position: " + position);
        SearchItem item = getItem(position);

        convertView = LayoutInflater.from(_context).inflate(R.layout.search_result_list_item, parent, false);

        TextView title = convertView.findViewById(R.id.textViewTitle);
        TextView year = convertView.findViewById(R.id.textViewYear);
        title.setText(item.Title);
        year.setText(item.Year);

        new LoadImageAsyncTask().execute(item.Poster, convertView.findViewById(R.id.imageViewPosterThumbnail));

        Button btn = convertView.findViewById(R.id.buttonDetails);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "details button clicked: " + position);
                Intent intent = new Intent(_context, DetailsActivity.class);
                intent.putExtra(DetailsActivity.IMDB_ID_KEY, item.imdbID);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(intent);
            }
        });


        return convertView;
    }
}
