package org.muferobotics.olympics.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.model.Schedule;

import java.util.ArrayList;
import java.util.List;

import android.widget.ImageView;
import android.widget.TextView;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    List<Schedule> items;

    public ScheduleAdapter(List<Schedule> items) {
        super();
        if (items == null) {
            items = new ArrayList<>();
        }
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_schedule, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Schedule schedule = items.get(i);
        viewHolder.title.setText(schedule.getTitle());
        viewHolder.time.setText(schedule.getTime());
        viewHolder.imgThumbnail.setImageResource(schedule.getThumbnail());
        viewHolder.description.setText(schedule.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView title;
        public TextView time;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.schedule_image);
            title = (TextView) itemView.findViewById(R.id.schedule_title);
            time = (TextView) itemView.findViewById(R.id.schedule_time);
            description = (TextView) itemView.findViewById(R.id.schedule_description);
        }
    }
}