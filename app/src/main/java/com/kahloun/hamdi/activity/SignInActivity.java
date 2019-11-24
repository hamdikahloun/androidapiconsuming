package com.kahloun.hamdi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.kahloun.hamdi.R;
import com.kahloun.hamdi.adapter.MyAdapter;
import com.kahloun.hamdi.mapping.AnswersListResponse;
import com.kahloun.hamdi.mapping.QuestionIDInput;
import com.kahloun.hamdi.mapping.SignInInput;
import com.kahloun.hamdi.mapping.UserResponse;
import com.kahloun.hamdi.utils.Viewing;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {
    private static final String tag = "SignInActivityTag";
    private Viewing viewing = new Viewing();
    private ProgressBar progressBar;
    private TextInputEditText email,password;
    private Button signIn;
    private Gson gson = new Gson();
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewing.setLightStatusBar(this);
        setContentView(R.layout.activity_sign_in);
        queue = Volley.newRequestQueue(this);
        progressBar = findViewById(R.id.progressBar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()) {
                    email.setError(getString(R.string.empty));

                }else if (password.getText().toString().isEmpty()) {
                    password.setError(getString(R.string.empty));

                } else {
                    signIn();
                }
            }
        });
    }

    private void signIn() {
        progressBar.setVisibility(View.VISIBLE);
        SignInInput signInInput = new SignInInput(email.getText().toString(),password.getText().toString());
        JSONObject json = null;
        try {
            json = new JSONObject(gson.toJson(signInInput));;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getString(R.string.signin), json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(tag,response.toString());
                        UserResponse userResponse = gson.fromJson(response.toString(),UserResponse.class);
                        if (userResponse.getCode() == 1) {
                            Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                            intent.putExtra(getString(R.string.user_info),gson.toJson(userResponse.getData()));
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(SignInActivity.this,userResponse.getError(),Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignInActivity.this, getString(R.string.error),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        queue.add(jsonObjectRequest);
    }
}
