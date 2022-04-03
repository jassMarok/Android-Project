package ca.mohawkcollege.marok;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageAsyncTask extends AsyncTask<Object, Void, Bitmap> {
    public static String TAG = "==DownloadImageTask==";
    public static int HTTP_OK = 200;

    private View _view;

    @Override
    protected Bitmap doInBackground(Object... params) {
        // Use the URL Connection interface to download the URL
        Bitmap bmp = null;
        Log.d(TAG, "do background loading image" + params[0]);

        _view = (View) params[1];

        // URL connection must be done in a try/catch
        int statusCode = -1;
        try {
            URL url = new URL((String)params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            statusCode = conn.getResponseCode();
            if (statusCode == HTTP_OK) {
                bmp = BitmapFactory.decodeStream(conn.getInputStream());
            }
        } catch (MalformedURLException e) {
            Log.d(TAG, "bad URL " + e);
        } catch (IOException e) {
            Log.d(TAG, "bad I/O " + e);
        }
        Log.d(TAG, "done " + statusCode);
        return bmp;
    }

    /**
     * after downloading is complete display the image
     * @param result - a bitmap image, null will skip the routine
     */
    protected void onPostExecute(Bitmap result) {
        Log.d(TAG,"onPostExecute()");
        if (result != null && _view != null) {
            ImageView albumArt = (ImageView) _view.findViewById(R.id.imageViewPosterThumbnail);
            albumArt.setImageBitmap(result);
        }
    }
}
