package android.example.thebookloft;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.core.widget.NestedScrollView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;


import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Action adapter1;
    private Adventure adapter2;
    private Romance adapter3;
    private Horror horror;
    private Mystery mystery;
    private Supernatural supernatural;
    private Thriller thriller;
    private Switch aSwitch;
    private int AdapterCount = 0;
    private ArrayList<Book> Books = new ArrayList<Book>();
   private  SearchView searchView;
    private static final int Book_Loader_Id = 1;
    private ImageView ActionCard;
    private ImageView AdventureCard;
    private ImageView MagicCard;
    private ImageView MusicCard;
    private ImageView SupernaturalCard;
    private ImageView ComedyCard;
    private ImageView FantasyCard;
    private ImageView CrimeCard;

    private ImageButton red;
    private ImageButton yellow;
    private ImageButton green;
    private ImageButton purple;
    private ImageButton blue;
    private ImageButton LightBlue;
    private ImageButton white;


    private  String search_Url = "https://www.googleapis.com/books/v1/volumes?";
    private static String [] Urls
            = {"https://www.googleapis.com/books/v1/volumes?q=subject:action", "https://www.googleapis.com/books/v1/volumes?q=subject:adventure", "https://www.googleapis.com/books/v1/volumes?q=subject:romance", "https://www.googleapis.com/books/v1/volumes?q=subject:Horror", "https://www.googleapis.com/books/v1/volumes?q=subject:Supernatural", "https://www.googleapis.com/books/v1/volumes?q=subject:thriller", "https://www.googleapis.com/books/v1/volumes?q=subject:mystery"};
    private LoaderManager loaderManager;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Log.v("Notice","onCreate");
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
       //toolbar.setTitleTextColor(getResources().getColor(R.color.colorDark));

       setSupportActionBar(toolbar);
         toolbar.setTitle(R.string.app_name);
       CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.CollapsingToolBar);
        mCollapsingToolbarLayout.setTitleEnabled(false);



      //toolbar.inflateMenu(R.menu.secondary_search_toolbar);

        ActionBar action = getSupportActionBar(); //get the actionbar
    //    action.setDisplayShowCustomEnabled(true); //enable it to display a
        // custom view in the action
        action.setTitle(R.string.app_name);
        //toolbar.inflateMenu(R.menu.search_toolbar);

        ActionCard =  findViewById(R.id.ActionCard);
        AdventureCard =  findViewById(R.id.AdventureCard);
        ComedyCard =  findViewById(R.id.ComedyCard);
        SupernaturalCard =  findViewById(R.id.SupernaturalCard);
        MusicCard =  findViewById(R.id.MusicCard);
        MagicCard =  findViewById(R.id.MagicCard);
        CrimeCard =  findViewById(R.id.CrimeCard);
        FantasyCard =  findViewById(R.id.FantasyCard);

        red =  findViewById(R.id.red);
        yellow =  findViewById(R.id.yellow);
        green =  findViewById(R.id.green);
        blue =  findViewById(R.id.blue);
        purple =  findViewById(R.id.purple);
        LightBlue =  findViewById(R.id.LightBlue);
        white =  findViewById(R.id.white);


       ActionCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Search_Results.class);
               intent.putExtra("query","subject:Action");
               startActivity(intent);
              // finish();
           }
       });
        MusicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Music");
                startActivity(intent);
                //finish();
            }
        });
        SupernaturalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Supernatural");
                startActivity(intent);
                //finish();
            }
        });
        AdventureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Adventure");
                startActivity(intent);
                //finish();
            }
        });
        CrimeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Crime");
                startActivity(intent);
               // finish();
            }
        });
        MagicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Magic");
                startActivity(intent);
               // finish();
            }
        });
        ComedyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Comedy");
                startActivity(intent);
               // finish();
            }
        });
        FantasyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Fantasy");
                startActivity(intent);
                //finish();
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Action");
                startActivity(intent);
                // finish();
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Adventure");
                startActivity(intent);
                //finish();
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Romance");
                startActivity(intent);
                //finish();
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Horror");
                startActivity(intent);
                //finish();
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Supernatural");
                startActivity(intent);
                // finish();
            }
        });
        LightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Mystery");
                startActivity(intent);
                // finish();
            }
        });
        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search_Results.class);
                intent.putExtra("query","subject:Thriller");
                startActivity(intent);
                // finish();
            }
        });






        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }else if(Intent.ACTION_VIEW.equals(intent.getAction())) {
            String selectedSuggestionRowId =  intent.getDataString();
           doMySearch(StoresData.stores.get(Integer.parseInt(selectedSuggestionRowId)-1));
            //execution comes here when an item is selected from search suggestions
            //you can continue from here with user selected search item
            Toast.makeText(this, "selected search suggestion "+selectedSuggestionRowId,
                    Toast.LENGTH_SHORT).show();
        }
          adapter1 = new Action(this, Books, AdapterCount);
          //adapter.setBooksModifier(this);
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.Genre_Action);
          layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
          recyclerView1.setLayoutManager(layoutManager);
          recyclerView1.setAdapter(adapter1);
           recyclerView1.scrollToPosition(10);
          AdapterCount++;
        //adapter = new BooksAdapter(this, Books, 1);
        adapter2 = new Adventure(this, Books, AdapterCount);
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.Genre_Adventure);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(adapter2);

         AdapterCount++;
       // adapter = new BooksAdapter(this, Books, 2);
        adapter3 = new Romance(this, Books, AdapterCount);
        RecyclerView recyclerView3 = (RecyclerView) findViewById(R.id.Genre_Romance);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setAdapter(adapter3);

        horror = new Horror(this, Books, AdapterCount);
         recyclerView3 = (RecyclerView) findViewById(R.id.Genre_Horror);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setAdapter(horror);

         mystery = new Mystery(this, Books, AdapterCount);
         recyclerView3 = (RecyclerView) findViewById(R.id.Genre_ScienceFiction);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setAdapter(mystery);

        supernatural = new Supernatural(this, Books, AdapterCount);
         recyclerView3 = (RecyclerView) findViewById(R.id.Genre_Supernatural);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setAdapter(supernatural);

        thriller = new Thriller(this, Books, AdapterCount);
        recyclerView3 = (RecyclerView) findViewById(R.id.Genre_Thriller);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setAdapter(thriller);

        loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(Book_Loader_Id, null, this);

    /*  aSwitch = findViewById(R.id.Theme);
      aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked){
                turnToDarkMode();
              }
              else{
                  turnToLightMode();
              }
          }
      });*/

    }

 /*   @Override
    public void onBookTouched(int Position) {

    }
*/
 void doMySearch(String query){

     Intent intent = new Intent(this, Search_Results.class);
     intent.putExtra("query",query);
     startActivity(intent);
     finish();
   /*uriBuilder.appendQueryParameter("limit", "10");
     uriBuilder.appendQueryParameter("minmag", minMagnitude);
     uriBuilder.appendQueryParameter("orderby", orderBy);*/
 }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.search_toolbar, menu);
         searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_button));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.search_button:
                // Do nothing for now
               /// Toast.makeText(MainActivity.this, "Search Button Clicked", Toast.LENGTH_SHORT).show();
                //     displayDatabaseInfo();
                super.onSearchRequested();
                Log.i("Check", "Icon Clicked");
                return true;
            // Respond to a click on the "Delete all entries" menu option
        }
        return super.onOptionsItemSelected(item);
    }
 @SuppressLint("ResourceType")
 public void turnToDarkMode() {
     NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.Frame);
    nestedScrollView.setBackgroundColor(this.getResources().getColor(R.color.cardview_dark_background));
 //    frameLayout.setBackgroundResource(this.getResources().getColor(R.color.cardview_dark_background));
     aSwitch.setTextColor(this.getResources().getColor(R.color.colorWhite));
    }
    public void turnToLightMode() {
        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.Frame);
        //frameLayout.setBackgroundResource(android.R.color.transparent);
       // nestedScrollView.setBackgroundResource(R.drawable.light_mode_3x);
        //aSwitch.setTextColor(this.getResources().getColor(R.color.colorWhite));
    }

    @NonNull
    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.v("Notice","onCreateLoader");
        return new BooksLoader(this, Urls);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        Log.v("Notice","onLoadFinished");
            adapter1.clear();
            adapter1.addAll(data);
            adapter2.clear();
            adapter2.addAll(data);
            adapter3.clear();
            adapter3.addAll(data);
        horror.clear();
        horror.addAll(data);
        supernatural.clear();
        supernatural.addAll(data);
        mystery.clear();
        mystery.addAll(data);
        thriller.clear();
        thriller.addAll(data);

    }

    @Override
    protected void onRestart() {
        adapter1.clear();
        adapter2.clear();
        adapter3.clear();
        horror.clear();
        supernatural.clear();
        mystery.clear();
        thriller.clear();
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
     SearchView searchView = (SearchView) findViewById(R.id.search_button);
        if(!searchView.isIconified()){
            searchView.setIconified(true);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Book>> loader) {
        Log.v("Notice","onLoaderReset");

        //adapter.setData(new ArrayList<ArrayList<Book>>());
        adapter1.clear();
        adapter2.clear();
        adapter3.clear();
        horror.clear();
        supernatural.clear();
        mystery.clear();
        thriller.clear();
    }
}