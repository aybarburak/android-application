package org.muferobotics.olympics.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.model.Announcement;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<Announcement> aItems;
    Context context;

    public CardAdapter(List<Announcement> aItems) {
        super();
        if (aItems == null) {
            aItems = new ArrayList<>();
        }
        this.aItems = aItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_announcement, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Announcement announcement = aItems.get(i);
        viewHolder.title.setText(announcement.getTitle());
        viewHolder.description.setText(announcement.getDescription());
        //TODO context
        Picasso.with(viewHolder.view.getContext()).load(announcement.getImage()).into(viewHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return aItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView title;
        public TextView description;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
