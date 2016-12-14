package org.muferobotics.olympics.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.model.Schedule;
import org.muferobotics.olympics.ui.adapter.ScheduleAdapter;
import org.muferobotics.olympics.ui.adapter.SimpleSectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DrawerFragment extends Fragment {
    private static final String KEY_TITLE = "title";
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    List<Schedule> scheduleList = new ArrayList<>();
    Schedule schedule;

    public DrawerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_schedule, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        scheduleLists();
        mAdapter = new ScheduleAdapter(scheduleList);

        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0, getString(R.string.robot_saturday)));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(11, getString(R.string.robot_sunday)));

        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(this.getActivity(),R.layout.section,R.id.section_text,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        mRecyclerView.setAdapter(mSectionedAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    public void scheduleLists(){
        String[] title = {
                getString(R.string.robot_opening),
                getString(R.string.robot_color),
                getString(R.string.robot_flag),
                getString(R.string.robot_sumo),
                getString(R.string.robot_free),
                getString(R.string.robot_break),
                getString(R.string.robot_seminar_bilge),
                getString(R.string.robot_seminar_pavotek),
                getString(R.string.robot_flag),
                getString(R.string.robot_istanbul),
                getString(R.string.robot_sumo),
                getString(R.string.robot_fire),
                getString(R.string.robot_sumo),
                getString(R.string.robot_free),
                getString(R.string.robot_seminar_bimeks),
                getString(R.string.robot_break),
                getString(R.string.robot_sumo),
                getString(R.string.robot_tenis),
                getString(R.string.robot_prize),
        };
        String[] time = {
                "09.00 - 11.00",
                "11.00 - 13.00",
                "11.00 - 13.00",
                "11.00 - 13.00",
                "11.00 - 18.00",
                "13.00 - 14.00",
                "14.00 - 15.00",
                "15.00 - 16.00",
                "14.00 - 18.00",
                "15.30 - 18.00",
                "16.00 - 18.00",
                "09.00 - 12.00",
                "09.00 - 12.00",
                "09.00 - 17.00",
                "12.00 - 13.00",
                "13.00 - 14.00",
                "14.00 - 17.00",
                "14.00 - 17.00",
                "17.00 - 18.00",
        };

        Integer[] img = {11,3,5,4,7,9,8,8,5,0,4,1,4,7,8,9,4,2,10};

        Integer[] desc = {0,1,1,0,1,2,0,0,1,1,0,1,0,1,0,2,0,1,0};

        for (int i = 0; i < title.length; i++) {
            schedule= new Schedule();
            schedule.setTime(time[i]);
            schedule.setTitle(title[i]);
            schedule.setThumbnail(img[i]);
            schedule.setDescription(desc[i]);
            scheduleList.add(schedule);
        }
    }
}
