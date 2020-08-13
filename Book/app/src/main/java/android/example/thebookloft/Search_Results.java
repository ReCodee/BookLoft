package android.example.thebookloft;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class Search_Results extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {
 private String [] Query_Url = new String[1];
private SearchAdapter adapter4;
private RecyclerView ResultsView;
private LoaderManager loaderManager;
private GridLayoutManager layoutManager;
private String query = "No Title";
private  ActionBar action;
    private int AdapterCount = 0;
    private ArrayList<Book> Books = new ArrayList<>();
    private static final int Book_Loader_Id = 1;
    private  String search_Url = "https://www.googleapis.com/books/v1/volumes?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__results);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
             query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarForResults);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        //toolbar.setTitleTextColor(getResources().getColor(R.color.colorDark));

        setSupportActionBar(toolbar);
              CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.CollapsingToolBar);
        mCollapsingToolbarLayout.setTitleEnabled(false);
       action = getSupportActionBar();
        action.setTitle(query);

        //toolbar.inflateMenu(R.menu.secondary_search_toolbar);

        String Activity_Title = getIntent().getStringExtra("query");
String [] SpString = Activity_Title.split(":");
  if(SpString.length == 2){
      query = SpString[1];
  }
  else {
      query = Activity_Title;
  }
        Uri baseUri = Uri.parse(search_Url);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", Activity_Title);
        Query_Url[0] = uriBuilder.toString();

        if(!TextUtils.isEmpty(Query_Url[0])){
            Toast.makeText(this, "Url Received", Toast.LENGTH_SHORT).show();
            assert Query_Url != null;
            Log.i("Url ", Query_Url[0]);
        }
        else{
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
            Log.i("Url ", "Empty h sab");
        }
        ResultsView = (RecyclerView) findViewById(R.id.Search_Results);
        adapter4 = new SearchAdapter(Search_Results.this, Books, AdapterCount);
        layoutManager = new GridLayoutManager(Search_Results.this, 3);
        ResultsView.setLayoutManager(layoutManager);
        ResultsView.setAdapter(adapter4);

        /*ResultsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book CurrentBook = adapter4.getItem(position);
                Uri earthquake = Uri.parse(CurrentBook.getWebLink());
                Intent WebsiteIntent = new Intent(Intent.ACTION_VIEW, earthquake);
                startActivity(WebsiteIntent);

            }
        });*/
ResultsView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.i("Web", "View Clicked");
    }
});

        loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(Book_Loader_Id, null, this);

    }

    void doMySearch(String query){

        Intent intent = new Intent(this, Search_Results.class);
        intent.putExtra("query",query);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        action.setTitle(query);
        getMenuInflater().inflate(R.menu.secondary_search_toolbar, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_button2));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


    @NonNull
    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.v("Data","onCreateLoader");
        return new BooksLoader(this, Query_Url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        Log.v("Data","onLoadFinished");
        adapter4.clear();
        adapter4.addAll(data);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Book>> loader) {
        Log.v("Data","onLoaderReset");

        //adapter.setData(new ArrayList<ArrayList<Book>>());
        adapter4.clear();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        SearchView searchView = (SearchView) findViewById(R.id.search_button2);
        if(!searchView.isIconified()){
            searchView.setIconified(true);
        }
        else{
            super.onBackPressed();
        }
    }
}