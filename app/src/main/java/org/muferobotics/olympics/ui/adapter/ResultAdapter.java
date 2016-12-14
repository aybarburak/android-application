package org.muferobotics.olympics.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.model.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    List<Result> aItems;
    Context context;

    public ResultAdapter(List<Result> aItems) {
        super();
        if (aItems == null) {
            aItems = new ArrayList<>();
        }
        this.aItems = aItems;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_result, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Result result = aItems.get(i);
        viewHolder.name.setText(result.getName());
        viewHolder.point.setText(result.getPoints().toString());
        viewHolder.time.setText(result.getTime().toString());
    }

    @Override
    public int getItemCount() {
        return aItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView point;
        public TextView time;
        public RelativeLayout row;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.robot_name);
            point = (TextView) itemView.findViewById(R.id.robot_points);
            time = (TextView) itemView.findViewById(R.id.robot_time);
        }
    }
}
