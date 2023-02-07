package com.example.inforapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    List<UserModel> items;
    ItemOnClick itemOnClick;

    public UserAdapter(List<UserModel> items, ItemOnClick itemOnClick) {
        this.items = items;
        this.itemOnClick = itemOnClick;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_layout, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.avtar = view.findViewById(R.id.button_avt);
            viewHolder.email = view.findViewById(R.id.text_email);
            viewHolder.username = view.findViewById(R.id.text_username);
            viewHolder.phone = view.findViewById(R.id.text_phone);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        UserModel user = items.get(i);

        viewHolder.avtar.setText(user.getAvatar());
        viewHolder.username.setText(user.getName());
        viewHolder.phone.setText(user.getPhone());
        viewHolder.email.setText(user.getEmail());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClick.OnItemClick(i);
            }
        });

        return view;
    }

    static class ViewHolder {
        Button avtar;
        TextView username;
        TextView email;
        TextView phone;
    }
}
