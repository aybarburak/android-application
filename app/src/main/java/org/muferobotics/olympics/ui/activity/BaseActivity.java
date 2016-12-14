package org.muferobotics.olympics.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.muferobotics.olympics.core.App;
import org.muferobotics.olympics.core.Cache;
import org.muferobotics.olympics.rest.MUFEClient;
import org.muferobotics.olympics.R;

import butterknife.Bind;
import butterknife.ButterKnife;

abstract class BaseActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @LayoutRes
    protected abstract int getContentViewLayoutResId();

    /**
     * Title could be changed before toolbar is created using this method
     *
     * @param toolbar
     */
    protected abstract void setUpToolbar(Toolbar toolbar);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewLayoutResId());
        ButterKnife.bind(this);

        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpToolbar(toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void enableToolbarNavigation() {
        this.setToolbarNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }

    protected void setToolbarNavigationIcon(int resourceId) {
        toolbar.setNavigationIcon(resourceId);
    }

    App getApp() {
        return (App) getApplication();
    }

    MUFEClient getClient() {
        return getApp().getMufeClient();
    }

    Cache getCache() {
        return getApp().getCache();
    }
}

