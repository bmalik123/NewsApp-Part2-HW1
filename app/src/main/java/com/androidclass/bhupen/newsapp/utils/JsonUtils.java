package com.androidclass.bhupen.newsapp.utils;

import com.androidclass.bhupen.newsapp.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {
    public static ArrayList<NewsItem> parseNews(String parsingString){
        ArrayList<NewsItem> newsList = new ArrayList<>();
        try {
            //JSONArray items = parsingString.getJSONArray("articles");
            JSONObject jsonObject = new JSONObject(parsingString);
            JSONArray items = jsonObject.getJSONArray("articles");

            for(int noOfItems = 0; noOfItems < items.length(); noOfItems++){
                JSONObject item = items.getJSONObject(noOfItems);
                newsList.add(new NewsItem(item.getString("title"), item.getString("description"),item.getString("url"),item.getString("publishedAt")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }


}


