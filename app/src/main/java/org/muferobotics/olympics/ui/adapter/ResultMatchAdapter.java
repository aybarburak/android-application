package org.muferobotics.olympics.ui.adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.model.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultMatchAdapter extends RecyclerView.Adapter<ResultMatchAdapter.ViewHolder> {

    List<Result> aItems;

    public ResultMatchAdapter(List<Result> aItems) {
        super();
        if (aItems == null) {
            aItems = new ArrayList<>();
        }
        this.aItems = aItems;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_result_matches, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Result result = aItems.get(i);
        viewHolder.round.setText(result.getRound().toString());
        viewHolder.winner.setText(result.getWinner());
        viewHolder.defeated.setText(result.getDefeated());
        viewHolder.defeated.setPaintFlags(viewHolder.defeated.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    public int getItemCount() {
        return aItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView round;
        public TextView winner;
        public TextView defeated;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            round = (TextView) itemView.findViewById(R.id.round);
            winner = (TextView) itemView.findViewById(R.id.winner);
            defeated = (TextView) itemView.findViewById(R.id.defeated);
        }
    }
}
