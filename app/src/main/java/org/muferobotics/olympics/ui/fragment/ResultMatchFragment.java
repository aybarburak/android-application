package org.muferobotics.olympics.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.core.ApiConstants;
import org.muferobotics.olympics.model.Result;
import org.muferobotics.olympics.model.rest.request.ResultsRequest;
import org.muferobotics.olympics.ui.adapter.ResultMatchAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ResultMatchFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    List<Result> resultList = new ArrayList<>();
    Result result;
    private static final String CATEGORY = "0";

    public ResultMatchFragment() {

    }

    public static ResultMatchFragment newInstance(String title) {
        ResultMatchFragment f = new ResultMatchFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, title);
        f.setArguments(args);
        return (f);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_result_match, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ResultMatchAdapter(resultList);
        mRecyclerView.setAdapter(mAdapter);


        getClient().getAppService().results(new ResultsRequest(ApiConstants.REQUEST_KEY,
                getArguments().getString(CATEGORY)), new Callback<List<Result>>() {
            @Override
            public void success(List<Result> results, Response response) {
                resultList.clear();
                if (results != null) {
                    resultList.clear();
                    resultList.addAll(results);

                    Collections.sort(resultList, new Comparator<Result>() {
                        public int compare(Result p1, Result p2) {
                            return p2.getRound().compareTo(p1.getRound());
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getResponse() != null) {
                    if (error.getResponse().getStatus() == 499) {
                        Log.e("Result Activity", "Invalid API Key");
                    } else if (error.getResponse().getStatus() == 500) {
                        Log.e("Result Activity", error.getResponse().toString());
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}