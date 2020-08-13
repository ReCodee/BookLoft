package android.example.thebookloft;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SuggestionsContentProvider extends ContentProvider {
    private static final String STORES = "stores/"+ SearchManager.SUGGEST_URI_PATH_QUERY+"/*";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI("com.BookLoft.books.search", STORES, 1);
    }

    private static String[] matrixCursorColumns = {"_id",
            SearchManager.SUGGEST_COLUMN_TEXT_1,
                  SearchManager.SUGGEST_COLUMN_INTENT_DATA };

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        String queryType = "";
        switch(uriMatcher.match(uri)){
            case 1 :
                String query = uri.getLastPathSegment().toLowerCase();
                return getSearchResultsCursor(query);
            default:
                return null;
        }
    }

    private MatrixCursor getSearchResultsCursor(String searchString){
        MatrixCursor searchResults =  new MatrixCursor(matrixCursorColumns);
        Object[] mRow = new Object[3];
        int counterId = 0;
        if(searchString != null){
            searchString = searchString.toLowerCase();

            for(String rec :  StoresData.getStores()){
                if(rec.toLowerCase().contains(searchString)){
                    mRow[0] = ""+counterId++;
                    mRow[1] = rec;
                    mRow[2] = ""+counterId++;

                    searchResults.addRow(mRow);
                }
            }
        }
        return searchResults;
    }

    private String getStoreName(String suggestion){
        String[] suggestionWords = suggestion.split(" ");
        return suggestionWords[0].toLowerCase();
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
