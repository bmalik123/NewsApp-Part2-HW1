package com.androidclass.bhupen.newsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidclass.bhupen.newsapp.utils.JsonUtils;
import com.androidclass.bhupen.newsapp.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

//import android.widget.EditText;
//import java.io.IOException;
//import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private TextView mUrlDisplayTextView;
    // private EditText mSearchBoxEditText;
    private TextView mSearchResultsTextView;
    private ProgressBar progressBar;
    //  Create a variable to store a reference to the error message TextView

    // TextView errorMessageTextView;
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private ArrayList<NewsItem> mNewsItems = new ArrayList<>();


    /*To initialize the activity onCreate method is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mSearchBoxEditText = (EditText) findViewById(R.id.news_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.news_url_display);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        mAdapter = new NewsRecyclerViewAdapter(this, mNewsItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSearchResultsTextView = (TextView) findViewById(R.id.news_search_results_json);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_loading_indicator);


    }
    // Get a reference to the error TextView using findViewById
    // errorMessageTextView=(TextView) findViewById(R.id.news_error_message_display);
    //  Get a reference to the ProgressBar using findViewById
    // progressBar=(ProgressBar) findViewById(R.id.progressbar_loading_indicator)

    /**
     * This method retrieves the search text from the EditText, constructs the URL
     */
    private URL makeNewsSearchQuery() {
        // String newsQuery = mSearchBoxEditText.getText().toString();
        mAdapter.mNewsItems.removeAll(mNewsItems);
        URL newsSearchUrl = NetworkUtils.buildURL();
        mUrlDisplayTextView.setText(newsSearchUrl.toString());
        // new NewsQueryTask().execute(newsSearchUrl);
        String StringNewsUrl = newsSearchUrl.toString();
        Log.d("news", StringNewsUrl);
        return newsSearchUrl;
       /* String NewsSearchResults = null;
        new GithubQueryTask().execute(NewsSearchUrl);
        try {
            NewsSearchResults = NetworkUtils.getResponseFromHttpUrl(NewsSearchUrl);
            mSearchResultsTextView.setText(NewsSearchResults);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    //  Created a method called showJsonDataView to show the data and hide the error

    /*public void showJsonDataView(){
        errorMessageTextView.setVisibility(View.INVISIBLE);
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }
    //  Created a method called showErrorMessage to show the error and hide the data

    public void showErrorMessage(){
        errorMessageTextView.setVisibility(View.VISIBLE);
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
    }*/


    public class NewsQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        /**
         * Override the doInBackground method to perform the query. Return the results.
         */
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String newsResults = "";
            try {
                newsResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsResults;
        }

        /**
         * Override onPostExecute to display the results in the TextView
         */
        @Override
        protected void onPostExecute(String newsResults) {
            Log.d("mycode", newsResults);
            super.onPostExecute(newsResults);
            progressBar.setVisibility(View.GONE);
            mNewsItems = JsonUtils.parseNews(newsResults);
            mAdapter.mNewsItems.addAll(mNewsItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * android:id--> A resource ID that's unique to the item, which allows the application to recognize the item when the user selects it.
     * android:icon--> A reference to a drawable to use as the item's icon.
     * android:title--> A reference to a string to use as the item's title.
     * android:showAsAction-->Specifies when and how this item should appear as an action item in the app bar.
     * <p>
     * onCreateOptionsMenu-->The options menu is where you should include actions and other options that are relevant to the current activity context,
     * such as "Search," "Compose email," and "Settings."
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**Inflate the menu; this adds items to the action bar if it is present.*/
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * onOptionsItemSelected-->When the user selects an item from the options menu (including action items in the app bar),
     * the system calls your activity's onOptionsItemSelected() method.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.get_news) {
            mUrlDisplayTextView.setVisibility(View.GONE);
            mSearchResultsTextView.setVisibility(View.GONE);
            URL url = makeNewsSearchQuery();
            NewsQueryTask task = new NewsQueryTask();
            task.execute(url);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

