package com.example.cibi_.news_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.R.color.white;

/**
 * Created by cibi_ on 7/29/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    JSONArray mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImgView;
        public TextView mTextView,mdesc,mlink;
        public View vi;
        public ViewHolder(View v) {
            super(v);
            mImgView = (ImageView) v.findViewById(R.id.images);
            mTextView = (TextView) v.findViewById(R.id.title);
            mdesc = (TextView) v.findViewById(R.id.desc);
            mlink = (TextView) v.findViewById(R.id.minfo);
            vi = v;

        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(JSONArray myDataset) {
        mDataset = myDataset;
    }
    Context ct;
    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
                ct = parent.getContext();
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    String s;
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //Toast.makeText(ct,(position+""),Toast.LENGTH_SHORT).show();
       ImageView img = holder.mImgView;
        JSONObject j = null;
        final TextView t = holder.mlink;
        try {
            j = mDataset.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            holder.mTextView.setText( j.get("title").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            holder.mdesc.setText(j.get("description").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
             s = j.get("url").toString();
            holder.mlink.setText(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setAction(Intent.ACTION_VIEW);
                in.addCategory(Intent.CATEGORY_BROWSABLE);

                in.setData(Uri.parse(t.getText().toString()));
                ct.startActivity(in);
            }
        });
       /* img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setAction(Intent.ACTION_VIEW);
                in.addCategory(Intent.CATEGORY_BROWSABLE);
                in.setData(Uri.parse(s));
                ct.startActivity(in);
            }
        });
        holder.mdesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setAction(Intent.ACTION_VIEW);
                in.addCategory(Intent.CATEGORY_BROWSABLE);
                in.setData(Uri.parse(s));
                ct.startActivity(in);
            }
        });
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setAction(Intent.ACTION_VIEW);
                in.addCategory(Intent.CATEGORY_APP_BROWSER);
                in.setData(Uri.parse(s));
                ct.startActivity(in);
            }
        });*/
        try {
            Picasso.with(ct).load(Uri.parse(j.get("urlToImage").toString())).into(img);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length();
    }
}
