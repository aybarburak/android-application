package org.muferobotics.olympics.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.muferobotics.olympics.core.ApiConstants;
import org.muferobotics.olympics.core.App;
import org.muferobotics.olympics.model.Robot;
import org.muferobotics.olympics.model.User;
import org.muferobotics.olympics.model.rest.request.ListRobotsRequest;
import org.muferobotics.olympics.ui.adapter.RobotCardAdapter;
import org.muferobotics.olympics.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RobotActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    private App app;
    private User user;
    private String key;


    List<Robot> robotList = new ArrayList<>();

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

        final ProgressDialog progressDialog = new ProgressDialog(RobotActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.activity_robot_listing));
        progressDialog.show();

        app = (App) getApplication();
        user = app.getCache().getUser();
        key = app.getCache().getUserAccessToken();

        getClient().getAppService().listRobots(new ListRobotsRequest(user.getEmail(),key,ApiConstants.REQUEST_KEY), new Callback<List<Robot>>() {
            @Override
            public void success(List<Robot> robots, Response response) {
                progressDialog.dismiss();
                if (robots != null) {
                    robotList.clear();
                    robotList.addAll(robots);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                if (error.getResponse() != null){
                    if (error.getResponse().getStatus() == 400) {
                        Log.e("Robot Activity", "Parameter is missing");
                    } else if (error.getResponse().getStatus() == 401) {
                        Log.e("Robot Activity", "Invalid Key");
                    } else if (error.getResponse().getStatus() == 499) {
                        Log.e("Robot Activity", "Invalid API Key");
                    } else if (error.getResponse().getStatus() == 500) {
                        Log.e("Robot Activity", error.getResponse().toString());
                    }
                }

            }
        });


    }

    private void setAdapter() {
        mAdapter = new RobotCardAdapter(robotList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
