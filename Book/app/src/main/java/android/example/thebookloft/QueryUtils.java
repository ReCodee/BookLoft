package android.example.thebookloft;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class QueryUtils {
    private static Book info;
    private static ArrayList<Book> BookInfo;
    private static ArrayList<Book> Results;
    private static final String LOG_TAG = QueryUtils.class.getName();
    private static String[] TitleAction;
    private static String[] UrlAction;
    private static String[] WebReaderLinkAction;
    private static String[] TitleAdventure;
    private static String[] UrlAdventure;
    private static String[] WebReaderLinkAdventure;
    private static String[] TitleRomance;
    private static String[] UrlRomance;
    private static String[] WebReaderLinkRomance;
    private static String[] TitleHorror;
    private static String[] UrlHorror;
    private static String[] WebReaderLinkHorror;
    private static String[] TitleSupernatural;
    private static String[] UrlSupernatural;
    private static String[] WebReaderLinkSupernatural;
    private static String[] TitleMystery;
    private static String[] UrlMystery;
    private static String[] WebReaderLinkMystery;
    private static String[] TitleThriller;
    private static String[] UrlThriller;
    private static String[] WebReaderLinkThriller;
    private static String[] Title;
    private static String[] Url;
    private static String[] WebReaderLink;

    public static String[] fetchDataForSuggestions(String requestUrl) throws JSONException {
        String[] Names;
        String jsonResponseArray = new String();
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        try {
            jsonResponseArray = makeHttpRequest(url);
            Log.i("Notice", "fetchEarthquakeData");
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        return extractBookNames(jsonResponseArray);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<Book> fetchBooksData(String[] requestUrl) throws JSONException {
        Log.v("Notice", "fetchBooksData");
        BookInfo = new ArrayList<Book>();
        Results = new ArrayList<Book>();
        ArrayList<Book> books = new ArrayList<Book>();

        Log.i("Size", "" + requestUrl.length);
        String[] jsonResponseArray = new String[7];
        for (int i = 0; i < requestUrl.length; ++i) {
            URL url = createUrl(requestUrl[i]);

            // Perform HTTP request to the URL and receive a JSON response back
            try {
                jsonResponseArray[i] = makeHttpRequest(url);
                Log.i("Notice", "fetchEarthquakeData");
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error closing input stream", e);
            }

            if (requestUrl.length == 1) {
                Log.i("Data", jsonResponseArray[i]);
            } else {
                Log.i("Genre " + i, jsonResponseArray[i]);
            }
        }
        if (requestUrl.length == 1) {
            extractBooksForSearchResults(jsonResponseArray[0]);
            InjectSearchResults();
            return Results;
        } else {
            extractBooksForActionGenre(jsonResponseArray[0]);
            extractBooksForAdventureGenre(jsonResponseArray[1]);
            extractBooksForRomanceGenre(jsonResponseArray[2]);
            extractBooksForHorrorGenre(jsonResponseArray[3]);
            extractBooksForSupernaturalGenre(jsonResponseArray[4]);
            extractBooksForMysteryGenre(jsonResponseArray[5]);
            extractBooksForThrillerGenre(jsonResponseArray[6]);
            InjectToBookInfo();
            return BookInfo;
        }
    }

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);

        }
        return url;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;

        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    public static void extractBooksForActionGenre(String jsonResponse) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);
            JSONArray array = object.getJSONArray("items");
            TitleAction = new String[array.length()];
            UrlAction = new String[array.length()];
            WebReaderLinkAction = new String[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONObject earthquake = array.getJSONObject(j);
                JSONObject eu = earthquake.getJSONObject("volumeInfo");
                JSONObject WebLink = earthquake.getJSONObject("accessInfo");
                WebReaderLinkAction[j] = WebLink.getString("webReaderLink");
                TitleAction[j] = eu.getString("title");
                JSONObject e = eu.getJSONObject("imageLinks");

                //long time = Long.parseLong(e.getString("time"));
                // DecimalFormat format = new DecimalFormat("0.0");
                //String Magnitude = format.format(Double.valueOf(e.getString("mag")));
                UrlAction[j] = e.getString("thumbnail");

            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }


        // Return the list of earthquakes

    }

    public static void extractBooksForAdventureGenre(String jsonResponse) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);
            JSONArray array = object.getJSONArray("items");
            TitleAdventure = new String[array.length()];
            UrlAdventure = new String[array.length()];
            WebReaderLinkAdventure = new String[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONObject earthquake = array.getJSONObject(j);
                JSONObject eu = earthquake.getJSONObject("volumeInfo");
                TitleAdventure[j] = eu.getString("title");
                JSONObject WebLink = earthquake.getJSONObject("accessInfo");
                WebReaderLinkAdventure[j] = WebLink.getString("webReaderLink");
                JSONObject e = eu.getJSONObject("imageLinks");
                //long time = Long.parseLong(e.getString("time"));
                // DecimalFormat format = new DecimalFormat("0.0");
                //String Magnitude = format.format(Double.valueOf(e.getString("mag")));
                UrlAdventure[j] = e.getString("thumbnail");


            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }


        // Return the list of earthquakes

    }

    public static void extractBooksForRomanceGenre(String jsonResponse) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);
            JSONArray array = object.getJSONArray("items");
            TitleRomance = new String[array.length()];
            UrlRomance = new String[array.length()];
            WebReaderLinkRomance = new String[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONObject earthquake = array.getJSONObject(j);
                JSONObject eu = earthquake.getJSONObject("volumeInfo");
                JSONObject WebLink = earthquake.getJSONObject("accessInfo");
                WebReaderLinkRomance[j] = WebLink.getString("webReaderLink");
                TitleRomance[j] = eu.getString("title");
                JSONObject e = eu.getJSONObject("imageLinks");
                //long time = Long.parseLong(e.getString("time"));
                // DecimalFormat format = new DecimalFormat("0.0");
                //String Magnitude = format.format(Double.valueOf(e.getString("mag")));
                UrlRomance[j] = e.getString("smallThumbnail");
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }


        // Return the list of earthquakes

    }

    public static void extractBooksForHorrorGenre(String jsonResponse) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);
            JSONArray array = object.getJSONArray("items");
            TitleHorror = new String[array.length()];
            UrlHorror = new String[array.length()];
            WebReaderLinkHorror = new String[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONObject earthquake = array.getJSONObject(j);
                JSONObject eu = earthquake.getJSONObject("volumeInfo");
                JSONObject WebLink = earthquake.getJSONObject("accessInfo");
                WebReaderLinkHorror[j] = WebLink.getString("webReaderLink");
                TitleHorror[j] = eu.getString("title");
                JSONObject e = eu.getJSONObject("imageLinks");
                //long time = Long.parseLong(e.getString("time"));
                // DecimalFormat format = new DecimalFormat("0.0");
                //String Magnitude = format.format(Double.valueOf(e.getString("mag")));
                UrlHorror[j] = e.getString("smallThumbnail");
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }


        // Return the list of earthquakes

    }

    public static void extractBooksForMysteryGenre(String jsonResponse) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);
            JSONArray array = object.getJSONArray("items");
            TitleMystery = new String[array.length()];
            UrlMystery = new String[array.length()];
            WebReaderLinkMystery = new String[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONObject earthquake = array.getJSONObject(j);
                JSONObject eu = earthquake.getJSONObject("volumeInfo");
                JSONObject WebLink = earthquake.getJSONObject("accessInfo");
                WebReaderLinkMystery[j] = WebLink.getString("webReaderLink");
                TitleMystery[j] = eu.getString("title");
                JSONObject e = eu.getJSONObject("imageLinks");
                //long time = Long.parseLong(e.getString("time"));
                // DecimalFormat format = new DecimalFormat("0.0");
                //String Magnitude = format.format(Double.valueOf(e.getString("mag")));
                UrlMystery[j] = e.getString("smallThumbnail");
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }


        // Return the list of earthquakes

    }

    public static void extractBooksForSupernaturalGenre(String jsonResponse) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);
            JSONArray array = object.getJSONArray("items");
            TitleSupernatural = new String[array.length()];
            UrlSupernatural = new String[array.length()];
            WebReaderLinkSupernatural = new String[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONObject earthquake = array.getJSONObject(j);
                JSONObject eu = earthquake.getJSONObject("volumeInfo");
                JSONObject WebLink = earthquake.getJSONObject("accessInfo");
                WebReaderLinkSupernatural[j] = WebLink.getString("webReaderLink");
                TitleSupernatural[j] = eu.getString("title");
                JSONObject e = eu.getJSONObject("imageLinks");
                //long time = Long.parseLong(e.getString("time"));
                // DecimalFormat format = new DecimalFormat("0.0");
                //String Magnitude = format.format(Double.valueOf(e.getString("mag")));
                UrlSupernatural[j] = e.getString("smallThumbnail");
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }


        // Return the list of earthquakes

    }

    public static void extractBooksForThrillerGenre(String jsonResponse) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);
            JSONArray array = object.getJSONArray("items");
            TitleThriller = new String[array.length()];
            UrlThriller = new String[array.length()];
            WebReaderLinkThriller = new String[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONObject earthquake = array.getJSONObject(j);
                JSONObject eu = earthquake.getJSONObject("volumeInfo");
                JSONObject WebLink = earthquake.getJSONObject("accessInfo");
                WebReaderLinkThriller[j] = WebLink.getString("webReaderLink");
                TitleThriller[j] = eu.getString("title");
                JSONObject e = eu.getJSONObject("imageLinks");
                //long time = Long.parseLong(e.getString("time"));
                // DecimalFormat format = new DecimalFormat("0.0");
                //String Magnitude = format.format(Double.valueOf(e.getString("mag")));
                UrlThriller[j] = e.getString("smallThumbnail");
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }


        // Return the list of earthquakes

    }

    public static void extractBooksForSearchResults(String jsonResponse) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);
            JSONArray array = object.getJSONArray("items");
            Title = new String[array.length()];
            Url = new String[array.length()];
            WebReaderLink = new String[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONObject earthquake = array.getJSONObject(j);
                JSONObject eu = earthquake.getJSONObject("volumeInfo");
                Title[j] = eu.getString("title");
                JSONObject WebLink = earthquake.getJSONObject("accessInfo");
                WebReaderLink[j] = WebLink.getString("webReaderLink");
                Log.i("Data", Title[j]);
                JSONObject e = eu.getJSONObject("imageLinks");
                //long time = Long.parseLong(e.getString("time"));
                // DecimalFormat format = new DecimalFormat("0.0");
                //String Magnitude = format.format(Double.valueOf(e.getString("mag")));
                Url[j] = e.getString("thumbnail");
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
    }

    public static String[] extractBookNames(String jsonResponse) throws JSONException {

        JSONObject object = new JSONObject(jsonResponse);
        JSONArray array = object.getJSONArray("items");
        String[] Names = new String[array.length()];
        for (int j = 0; j < array.length(); j++) {
            JSONObject earthquake = array.getJSONObject(j);
            JSONObject eu = earthquake.getJSONObject("volumeInfo");
            Names[j] = eu.getString("title");
        }
        return Names;
    }

    public static void InjectToBookInfo() {
        for (int i = 0; i < TitleAction.length; ++i) {
            BookInfo.add(new Book(TitleAction[i], UrlAction[i], TitleAdventure[i], UrlAdventure[i], TitleRomance[i], UrlRomance[i], WebReaderLinkAction[i], WebReaderLinkAdventure[i], WebReaderLinkRomance[i], WebReaderLinkHorror[i], WebReaderLinkSupernatural[i], WebReaderLinkMystery[i], WebReaderLinkThriller[i]
                    , UrlHorror[i], UrlSupernatural[i], UrlMystery[i], UrlThriller[i], TitleHorror[i], TitleSupernatural[i], TitleMystery[i], TitleThriller[i]));
            Log.v("Check", TitleHorror[i]);
        }
    }

    public static void InjectSearchResults() {
        for (int i = 0; i < Title.length; ++i) {
            Results.add(new Book(Title[i], Url[i], WebReaderLink[i]));
        }
    }
}


