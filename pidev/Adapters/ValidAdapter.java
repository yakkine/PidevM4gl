package com.example.rym.pidev.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import com.example.rym.pidev.Entites.Task;
import com.example.rym.pidev.R;



import java.util.List;

/**
 * Created by rym on 24/11/2017.
 */

public class ValidAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<Task> categItems;
    private String json_url_deleteTopic;
    Task c;

    public ValidAdapter(Activity activity, List<Task> categItems) {
        this.activity = activity;
        this.categItems = categItems;
    }


    @Override
    public int getCount() {
        return categItems.size();
    }

    @Override
    public Object getItem(int location) {
        return categItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.valid, null);


        TextView nom = (TextView) convertView.findViewById(R.id.txtnom);
        TextView description = (TextView) convertView.findViewById(R.id.txtdescription);
        TextView deadline = (TextView) convertView.findViewById(R.id.txttime);
        TextView state = (TextView) convertView.findViewById(R.id.txtstat);
       // TextView user = (TextView) convertView.findViewById(R.id.user);

        c = categItems.get(position);


        nom.setText(c.getName());
        description.setText(c.getDescription());
        deadline.setText(c.getDeadline());
        state.setText(c.getState());
        //user.setText(c.getUser());


        return convertView;
    }




    }








