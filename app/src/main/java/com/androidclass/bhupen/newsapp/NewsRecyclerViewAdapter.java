package com.androidclass.bhupen.newsapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {
    Context mContext;
    ArrayList<NewsItem> mNewsItems;
    String urlString;
    Intent intent;

    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> newsItems) {
        this.mContext = context;
        this.mNewsItems = newsItems;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,description,date;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
        }

        void bind(final int listIndex) {
            title.setText("Title: ".concat(mNewsItems.get(listIndex).getTitle()));
            description.setText("Description:  ".concat(mNewsItems.get(listIndex).getDescription()));
            date.setText("Date:   ".concat(mNewsItems.get(listIndex).getDate().substring(0, mNewsItems.get(listIndex).getDate().indexOf("T"))));
            itemView.setOnClickListener((View.OnClickListener)this);
        }

        @Override
        public void onClick(View view) {
            urlString = mNewsItems.get(getAdapterPosition()).getUrl();
            intent = new Intent(mContext, com.androidclass.bhupen.newsapp.WebActivity.class);
            intent.putExtra("urlString", urlString);
            mContext.startActivity(intent);
        }

    }


}


