package com.grimg.customlistview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.grimg.customlistview.models.Item;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Amine on 10/3/2017.
 */

public class CustomAdapter extends ArrayAdapter<Item> {

    private Context context;

    public CustomAdapter (Context context, int resourseId, List<Item> items) {

        super(context, resourseId, items);
        this.context = context;
    }

    private class ViewHolder {
        TextView txtTitle, txtDescription;
    }

    @NonNull
    public View getView (int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder = null;
        Item rowItem = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //convertView is the old getView(), we inflate the layout only when the iew row is created for the first time ,
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (rowItem != null) {
            holder.txtTitle.setText(rowItem.getTitle());
            holder.txtDescription.setText(rowItem.getDescription());
        }

        return  convertView;

    }

}
