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
import android.widget.LinearLayout;
import android.widget.TextView;


import com.herosky.hackathon.hackathonedumobileclient.ws.PBGTeacher_Student_Mapping;

import java.util.ArrayList;

/**
 * Created by Kduy on 3/20/2015.
 */
public class HistoryAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<PBGTeacher_Student_Mapping> persons = new ArrayList<PBGTeacher_Student_Mapping>();

    public HistoryAdapter(Context context, ArrayList<PBGTeacher_Student_Mapping> lists) {
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
        position--;
        HistoryHolder holder = new HistoryHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, null);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.message = (TextView)convertView.findViewById(R.id.textViewMessage);
            holder.receiver = (TextView)convertView.findViewById(R.id.textViewReceive);
            holder.status = (ImageView)convertView.findViewById(R.id.imageViewStatus);
            holder.root = (LinearLayout)convertView.findViewById(R.id.root);
            holder.pos = position;
            convertView.setTag(holder);
        } else {

            holder = (HistoryHolder) convertView.getTag();
            holder.pos = position;
        }
        if(position == -1)
        {
            holder.root.setBackgroundColor(Color.parseColor("#F1EFE2"));
            holder.receiver.setText("Receiver");
            holder.status.setImageDrawable(null);
            holder.message.setText("Message");
            holder.checkBox.setCompoundDrawablesRelative(null,null,null,null);
            holder.checkBox.setText("All");
        }
        else {
            holder.root.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.checkBox.setChecked(persons.get(position).isCheck);
            //holder.checkBox.setText(persons.get(position).student.FullName);
            holder.checkBox.setTag(position);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    persons.get((Integer) buttonView.getTag()).isCheck = isChecked;
                }
            });

            int status = 0;
            switch (status) {
                case 0:
                    holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.fail));
                    break;
                case 1:
                    holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ok));
                    break;
                case 2:
                    holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pending));
                    break;
            }

            String message = "";
            holder.message.setText(message);

            ArrayList<String> receivers = new ArrayList<>();
            String recei="";
            for(int i= 0; i < receivers.size();i++)
            {

                if (recei.length() > 40) {
                    recei += "...";

                }
                else {

                    recei += receivers.get(i)+ ", ";
                }


            }
            holder.receiver.setText(recei);
            String type = "exam";

            if (type.equals("exam")) {
                holder.checkBox.setCompoundDrawablesRelative(mContext.getResources().getDrawable(R.drawable.exam), null, null, null);
            } else if (type.equals("meeting")) {
                holder.checkBox.setCompoundDrawablesRelative(mContext.getResources().getDrawable(R.drawable.meeting), null, null, null);
            } else if (type.equals("comment")) {
                holder.checkBox.setCompoundDrawablesRelative(mContext.getResources().getDrawable(R.drawable.comment), null, null, null);
            }
        }
        //products.get(position).holder = holder;
        return convertView;
    }



    public static class HistoryHolder {
        ObjectAnimator animation;
        LinearLayout root;
        CheckBox checkBox;
        ImageView status;
        TextView message, receiver;

        int pos= -1;
        Boolean isSetImage = false;
    }

}

