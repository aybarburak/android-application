package org.muferobotics.olympics.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.core.ApiConstants;
import org.muferobotics.olympics.model.Announcement;
import org.muferobotics.olympics.model.Result;
import org.muferobotics.olympics.model.rest.request.GetAnnouncementsRequest;
import org.muferobotics.olympics.ui.adapter.CardAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class AnnouncementActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    List<Announcement> announcementList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutResId() {
        return R.layout.activity_announcement;
    }

    @Override
    protected void setUpToolbar(Toolbar toolbar) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableToolbarNavigation();

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        setAdapter();

        final ProgressDialog progressDialog = new ProgressDialog(AnnouncementActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.show();

        getClient().getAppService().getAnnouncements(new GetAnnouncementsRequest(ApiConstants.REQUEST_KEY,
                true), new Callback<List<Announcement>>() {
            @Override
            public void success(List<Announcement> announcements, Response response) {
                progressDialog.dismiss();
                if (announcements != null) {
                    announcementList.clear();
                    announcementList.addAll(announcements);
                    Collections.sort(announcementList, new Comparator<Announcement>() {
                        public int compare(Announcement p1, Announcement p2) {
                            return p2.getDate().compareTo(p1.getDate());
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                if (error.getResponse() != null) {
                    if (error.getResponse().getStatus() == 499) {
                        Log.e("Login Activity", "Invalid API Key");
                    } else if (error.getResponse().getStatus() == 500) {
                        Log.e("Login Activity", error.getResponse().toString());
                    }
                }
            }
        });
    }

    private void setAdapter() {
        mAdapter = new CardAdapter(announcementList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
