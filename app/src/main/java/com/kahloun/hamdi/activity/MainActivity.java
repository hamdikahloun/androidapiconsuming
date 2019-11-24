package com.kahloun.hamdi.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kahloun.hamdi.R;
import com.kahloun.hamdi.adapter.ScreenSlidePagerAdapter;
import com.kahloun.hamdi.fragment.QuestionFragment;
import com.kahloun.hamdi.mapping.QuestionListResponse;
import com.kahloun.hamdi.model.Question;
import com.kahloun.hamdi.model.User;
import com.kahloun.hamdi.utils.Viewing;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String tag = "MainActivityTag";
    private Viewing viewing = new Viewing();
    private ViewPager viewPager;
    private TextView username;
    private PagerAdapter pagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ProgressBar progressBar;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewing.setLightStatusBar(this);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        String intentString = intent.getStringExtra(getString(R.string.user_info));
        final User user = gson.fromJson(intentString,User.class);
        viewPager = findViewById(R.id.view_pager);
        progressBar = findViewById(R.id.progressBar);
        username = findViewById(R.id.username);
        username.setText(user.getName());
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        findViewById(R.id.profile_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.email)
                        .setTitle(R.string.hamdi_kahloun);
                final AlertDialog dialog = builder.create();
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.get_all_questions),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        QuestionListResponse questionsResponse = gson.fromJson(response, QuestionListResponse.class);
                        for (Question question : questionsResponse.getData()) {
                            fragmentList.add(QuestionFragment.newInstance(question.getQuestion(), question.getQuestion_id(),user.getUser_id()));
                        }
                        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),fragmentList);
                        viewPager.setAdapter(pagerAdapter);
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, getString(R.string.error),Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
