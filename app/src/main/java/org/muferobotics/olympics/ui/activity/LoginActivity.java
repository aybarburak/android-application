package org.muferobotics.olympics.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.muferobotics.olympics.core.ApiConstants;
import org.muferobotics.olympics.model.rest.request.LoginRequest;
import org.muferobotics.olympics.model.rest.response.UserResponse;
import org.muferobotics.olympics.R;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;

    @Override
    protected int getContentViewLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setUpToolbar(Toolbar toolbar) {
        toolbar.setTitle(getString(R.string.menu_login));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableToolbarNavigation();

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }
        _loginButton.setEnabled(false);

        /**
         * TODO: If you want to customize progress dialog, create a fully custom class that extends
         * ProgressDialog. Custom styles like R.style.AppTheme_Dark_Dialog could not be work in
         * earlier versions.
         */
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.authenticate));
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        getClient().getAppService().login(new LoginRequest(email, password, ApiConstants.REQUEST_KEY), new Callback<UserResponse>() {
            @Override
            public void success(UserResponse userResponse, Response response) {
                if (userResponse != null) {
                    if (userResponse.getUser() != null) {
                        progressDialog.dismiss();
                        onLoginSuccess(userResponse);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                if (error.getResponse() != null){
                    if (error.getResponse().getStatus() == 400) {
                        Log.e("Login Activity", "Parameter is missing");
                    } else if (error.getResponse().getStatus() == 401) {
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.activity_login_invalid_pass), Toast.LENGTH_SHORT).show();
                    } else if (error.getResponse().getStatus() == 402) {
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.activity_login_invalid_email), Toast.LENGTH_SHORT).show();
                    } else if (error.getResponse().getStatus() == 499) {
                        Log.e("Login Activity","Invalid API Key");
                    } else if (error.getResponse().getStatus() == 500) {
                        Log.e("Login Activity", error.getResponse().toString());
                    }
                }
                onLoginFailed();
            }
        });
    }

    public void onLoginSuccess(UserResponse userResponse) {
        getCache().setUserData(userResponse.getUser());
        getCache().setUserAccessToken(userResponse.getApiSecret());

        _loginButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(this, PrimaryActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.activity_login_failed), Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError(getResources().getString(R.string.email_failed));
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 40) {
            _passwordText.setError(getResources().getString(R.string.character_failed_10));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
