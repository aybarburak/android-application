package org.muferobotics.olympics.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.muferobotics.olympics.core.App;
import org.muferobotics.olympics.model.User;
import org.muferobotics.olympics.ui.fragment.DrawerFragment;
import org.muferobotics.olympics.R;

import butterknife.Bind;

public class PrimaryActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_LOGIN = 0;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    private App app;
    private User user;


    @Override
    protected int getContentViewLayoutResId() {
        return R.layout.activity_primary;
    }

    @Override
    protected void setUpToolbar(Toolbar toolbar) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        app = (App) getApplication();
        user = app.getCache().getUser();

        //Listen navigation view
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
        TextView fullname = (TextView)header.findViewById(R.id.nav_fullname);
        TextView email = (TextView)header.findViewById(R.id.nav_email);
        ImageView image = (ImageView)header.findViewById(R.id.nav_image);
        String url = "http://olimpiyat.muferobotics.org/" + user.getImage();
        Picasso.with(this).load(url).resize(200, 200).into(image);
        fullname.setText(user.getFirstName() + " " + user.getLastName());
        email.setText(user.getEmail());

        DrawerFragment f = new DrawerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contentFragment, f).commit();

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.announcement) {
            Intent intent = new Intent(this, AnnouncementActivity.class);
            startActivity(intent);
        } else if (id == R.id.map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.result) {
            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        } else if (id == R.id.edit_profile) {
            Intent intent = new Intent(this, EditActivity.class);
            startActivity(intent);
        } else if (id == R.id.robot) {
            Intent intent = new Intent(this, RobotActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            app = (App) getApplication();
            app.getCache().setUserData(null);
            app.getCache().setUserAccessToken(null);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }
}

