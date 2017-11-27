package com.esprit.pi.pidev;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.esprit.pi.pidev.model.User;

import java.util.List;

/**
 * Created by MBM info on 24/11/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> users;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);

        this.context=context;
        this.users= objects;
    }
    @Override
    public View getView(final int pos, View convertView , ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_user,parent,false);
        TextView txtUserId =(TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername =(TextView) rowView.findViewById(R.id.txtUsername);

        txtUserId.setText(String.format("user_id: %d",users.get(pos).getId()));
        txtUsername.setText(String.format("user_name: %s",users.get(pos).getFirstname()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start Activity user form
                Intent intent = new Intent(context , UserActivity.class);
                intent.putExtra("user_id",String.valueOf(users.get(pos).getId()));
                intent.putExtra("user_name",users.get(pos).getFirstname());
                context.startActivity(intent);
            }
        });
  return  rowView;

    }
}
