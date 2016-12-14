package org.muferobotics.olympics.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.muferobotics.olympics.core.ApiConstants;
import org.muferobotics.olympics.core.App;
import org.muferobotics.olympics.model.Modify;
import org.muferobotics.olympics.model.User;
import org.muferobotics.olympics.model.rest.request.ModifyUserRequest;
import org.muferobotics.olympics.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EditActivity extends BaseActivity {

    private App app;
    private User user;
    private String key;

    @Bind(R.id.edit_phone)
    EditText _phoneText;
    @Bind(R.id.edit_firstname)
    EditText _firstnameText;
    @Bind(R.id.edit_lastname)
    EditText _lastnameText;
    @Bind(R.id.edit_academy_information)
    EditText _academy_informationText;
    @Bind(R.id.btn_save)
    Button _saveButton;

    @Override
    protected int getContentViewLayoutResId() {
        return R.layout.activity_edit;
    }

    @Override
    protected void setUpToolbar(Toolbar toolbar) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableToolbarNavigation();

        app = (App) getApplication();
        user = app.getCache().getUser();

        _firstnameText.setText(user.getFirstName());
        _lastnameText.setText(user.getLastName());
        _academy_informationText.setText(user.getAcademyInformation());
        _phoneText.setText(user.getPhoneNumber());

        _saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edit();
            }
        });
    }

    public void edit() {

        if (!validate()) {
            onEditFailed();
            return;
        }

        _saveButton.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(EditActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.activity_edit_update));
        progressDialog.show();

        app = (App) getApplication();
        user = app.getCache().getUser();
        key = app.getCache().getUserAccessToken();

        ArrayList<Modify> modifications = new ArrayList<Modify>();

        modifications.add(new Modify("first_name",_firstnameText.getText().toString()));
        modifications.add(new Modify("last_name",_lastnameText.getText().toString()));
        modifications.add(new Modify("phone_number", _phoneText.getText().toString()));
        modifications.add(new Modify("academy_information", _academy_informationText.getText().toString()));


        getClient().getAppService().modifyUser(new ModifyUserRequest(user.getEmail(), key, modifications, ApiConstants.REQUEST_KEY), new Callback<List<JSONArray>>() {
            @Override
            public void success(List<JSONArray> jsonArrays, Response response) {
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), getResources().getString(R.string.activity_edit_updated), Toast.LENGTH_LONG).show();
                onEditSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                if (error.getResponse() != null){
                    if (error.getResponse().getStatus() == 400) {
                        Log.e("Edit Activity", "Parameter is missing");
                    } else if (error.getResponse().getStatus() == 401) {
                        Log.e("Edit Activity", "Invalid Key");
                    } else if (error.getResponse().getStatus() == 402) {
                        Log.e("Edit Activity", "Undefined Values");
                    } else if (error.getResponse().getStatus() == 499) {
                        Log.e("Edit Activity","Invalid API Key");
                    } else if (error.getResponse().getStatus() == 500) {
                        Log.e("Edit Activity", error.getResponse().toString());
                    }
                }
                onEditFailed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onEditSuccess() {
        _saveButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onEditFailed() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.activity_edit_update_failed), Toast.LENGTH_LONG).show();

        _saveButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String first_name = _firstnameText.getText().toString();
        String last_name = _lastnameText.getText().toString();
        String username = _academy_informationText.getText().toString();
        String phone_number = _phoneText.getText().toString();


        if (first_name.isEmpty() || first_name.length() < 4 || first_name.length() > 10) {
            _firstnameText.setError(getResources().getString(R.string.character_failed_10));
            valid = false;
        } else {
            _firstnameText.setError(null);
        }

        if (last_name.isEmpty() || last_name.length() < 4 || last_name.length() > 10) {
            _lastnameText.setError(getResources().getString(R.string.character_failed_10));
            valid = false;
        } else {
            _lastnameText.setError(null);
        }
        if (phone_number.isEmpty() || phone_number.length() < 4 || phone_number.length() > 13) {
            _phoneText.setError(getResources().getString(R.string.character_failed_13));
            valid = false;
        } else {
            _phoneText.setError(null);
        }
        if (username.isEmpty() || username.length() < 4 || username.length() > 30) {
            _academy_informationText.setError(getResources().getString(R.string.character_failed_30));
            valid = false;
        } else {
            _academy_informationText.setError(null);
        }

        return valid;
    }
}
