package com.singh.harsukh.retrofitpractice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by harsukh on 3/8/16.
 */
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /*
    1.) Reuse cells while scrolling up/down - this is possible with implementing View Holder
    in the listView adapter, but it was an optional thing, while in the RecycleView
    it's the default way of writing adapter.

    2.) Decouple list from its container - so you can put list
        items easily at run time in the different containers
        (linearLayout, gridLayout) with setting LayoutManager
     */
    ArrayList<RowPost.PostsBean> rows;

    public PostAdapter(ArrayList rows)
    {
        this.rows = rows;
    }

    /////THESE METHODS NEED TO BE OVERRIDEN/////////////////


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType == 1) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }
        if(viewType == 0)
        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.excerpt_rows, parent, false);
            ViewHolderExcerpt vh = new ViewHolderExcerpt(v);
            return vh;
        }
        // set the view's size, margins, paddings and layout parameters
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

//        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String datetime = dateformat.format(rows.get(position).getDate());
        if( holder instanceof ViewHolder) {
            ((ViewHolder)holder).Title.setText(rows.get(position).getTitle());
            ((ViewHolder)holder).Date.setText(rows.get(position).getDate());
        }
        if(holder instanceof ViewHolderExcerpt)
        {
            ((ViewHolderExcerpt)holder).Title.setText(rows.get(position).getTitle());
            ((ViewHolderExcerpt)holder).Excerpt.setText(rows.get(position).getExcerpt());
            ((ViewHolderExcerpt)holder).Date.setText(rows.get(position).getDate());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return rows.size();
    }

    @Override
    public int getItemViewType(int position) {
        RowPost.PostsBean post  = rows.get(position);
        if(post.getExcerpt() == null)
            return 1;
        else
            return 0;
    }



    /////THESE METHODS NEED TO BE OVERRIDEN/////////////////


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        protected TextView Title;
        protected TextView Date;
        public ViewHolder(View v) {
            super(v);
            Title = (TextView) v.findViewById(R.id.title);
            Date = (TextView) v.findViewById(R.id.date);
        }
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolderExcerpt extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        protected TextView Title;
        protected TextView Date;
        protected TextView Excerpt;
        public ViewHolderExcerpt(View v) {
            super(v);
            Title = (TextView) v.findViewById(R.id.excerpt_title);
            Date = (TextView) v.findViewById(R.id.excerpt_date);
            Excerpt = (TextView) v.findViewById(R.id.excerpt);
        }
    }
}
