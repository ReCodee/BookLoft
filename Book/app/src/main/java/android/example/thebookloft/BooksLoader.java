package android.example.thebookloft;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.loader.content.AsyncTaskLoader;
import org.json.JSONException;
import java.util.ArrayList;

public class BooksLoader extends AsyncTaskLoader<ArrayList<Book>> {
    private String [] mUrl ;
    public BooksLoader(Context context, String [] URL){
        super(context);
        Log.v("Notice","BooksLoader");
        mUrl = URL;
    }

    @Override
    protected void onStartLoading() {
        Log.v("Notice","onStartLoading");
        Log.i("Notice", "onStartLoading");
        forceLoad();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public ArrayList<Book> loadInBackground() {
        Log.v("Notice","loadInBackground");
       ArrayList<Book> Books = new ArrayList<Book>();
        try{

            Books = QueryUtils.fetchBooksData(mUrl);
            Log.i("Notice", "loadInBackground");

        } catch (JSONException e) {
            Log.i("Not Working", "loadInBackground");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return Books;
    }
}
