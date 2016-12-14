package org.muferobotics.olympics.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.muferobotics.olympics.model.Robot;
import org.muferobotics.olympics.R;

import java.util.ArrayList;
import java.util.List;

public class RobotCardAdapter extends RecyclerView.Adapter<RobotCardAdapter.ViewHolder>{

    List<Robot> aItems;

    public RobotCardAdapter(List<Robot> aItems) {
        super();
        if (aItems == null) {
            aItems = new ArrayList<>();
        }
        this.aItems = aItems;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_robot, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Robot robot = aItems.get(i);
        viewHolder.robotName.setText(robot.getName());
        viewHolder.categoryName.setText(robot.getCategory());
        viewHolder.imgThumbnail.setImageResource(robot.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return aItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView robotName;
        public TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            robotName = (TextView)itemView.findViewById(R.id.robot_name);
            categoryName = (TextView)itemView.findViewById(R.id.category_name);
        }
    }
}
