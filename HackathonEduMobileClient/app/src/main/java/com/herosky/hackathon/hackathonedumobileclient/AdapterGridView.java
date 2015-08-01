package com.herosky.hackathon.hackathonedumobileclient;



import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Kduy on 3/20/2015.
 */
public class AdapterGridView extends BaseAdapter {

    Context mContext;
    ArrayList<MainActivity.Person> persons = new ArrayList<MainActivity.Person>();

    public AdapterGridView(Context context, ArrayList<MainActivity.Person> lists) {
        mContext = context;
        persons = lists;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public final String tag = "product";

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonGridViewHolder holder = new PersonGridViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, null);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxAdd);
            holder.pos = position;
            convertView.setTag(holder);
        } else {

            holder = (PersonGridViewHolder) convertView.getTag();
            holder.pos = position;
        }

        holder.checkBox.setChecked(persons.get(position).isCheck);
        holder.checkBox.setText(persons.get(position).name);
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                persons.get((Integer) buttonView.getTag()).isCheck = isChecked;
            }
        });

        //products.get(position).holder = holder;
        return convertView;
    }



    public static class PersonGridViewHolder {
        ObjectAnimator animation;
        CheckBox checkBox;
        int pos= -1;
        Boolean isSetImage = false;
    }

}

