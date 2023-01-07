package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    List<ContactModel> items;
    ItemOnClick itemOnClick;

    public ContactAdapter(List<ContactModel> items, ItemOnClick itemOnClick) {
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
        return items.get(i).getID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_layout, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.buttonView = view.findViewById(R.id.button);
            viewHolder.textView = view.findViewById(R.id.textView2);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ContactModel contact = items.get(i);

        viewHolder.buttonView.setText("" + contact.getFullname().charAt(0));
        viewHolder.textView.setText(contact.getFullname());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClick.OnItemClick(i);
            }
        });
        view.setLongClickable(true);

        return view;
    }

    static class ViewHolder {
        Button buttonView;
        TextView textView;
    }
}
