package com.kahloun.hamdi.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.kahloun.hamdi.R;
import com.kahloun.hamdi.adapter.MyAdapter;
import com.kahloun.hamdi.mapping.AnswerInput;
import com.kahloun.hamdi.mapping.AnswerResponse;
import com.kahloun.hamdi.mapping.AnswersListResponse;
import com.kahloun.hamdi.mapping.QuestionIDInput;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class QuestionFragment extends Fragment {
    private static final String tag = "QuestionFragmentTag";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private String mParam1;
    private int mParam2;
    private int mParam3;
    private View view;
    private TextView question;
    private RecyclerView answer;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private FloatingActionButton addNewAnswer;
    private AlertDialog dialogNewAnswer;
    private RequestQueue queue;
    private Gson gson = new Gson();
    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(String param1, int param2,int param3) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_question, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        addNewAnswer = view.findViewById(R.id.addNewAnswer);
        viewPager = getActivity().findViewById(R.id.view_pager);
        question = view.findViewById(R.id.question);
        CardView cardView = view.findViewById(R.id.card_view);
        answer = view.findViewById(R.id.answer);
        answer.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        answer.setLayoutManager(layoutManager);
        question.setText(mParam1);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(50), rnd.nextInt(50), rnd.nextInt(50));
        cardView.setCardBackgroundColor(color);

        queue = Volley.newRequestQueue(getContext());

        addNewAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogButtonClicked();
            }
        });

        getAnswers();
        return view;
    }

    private void showAlertDialogButtonClicked() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(mParam1);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.new_answer, null);
        builder.setView(customLayout);
        // add a button
        builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // send data from the AlertDialog to the Activity
                EditText editText = customLayout.findViewById(R.id.editText);
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), getString(R.string.empty_answer),Toast.LENGTH_LONG).show();
                } else {
                    sendDialogDataToDataBase(editText.getText().toString());
                }

            }
        });

        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogNewAnswer.dismiss();
            }
        });
        // create and show the alert dialog
        dialogNewAnswer = builder.create();
        dialogNewAnswer.setCancelable(false);
        dialogNewAnswer.show();
    }
    // do something with the data coming from the AlertDialog
    private void sendDialogDataToDataBase(String data) {
        AnswerInput answerInput = new AnswerInput(data,mParam2,mParam3);
        JSONObject json = null;
        try { 
            json = new JSONObject(gson.toJson(answerInput));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getString(R.string.add_new_answer), json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(tag,response.toString());
                        AnswerResponse newAnswerResponse = gson.fromJson(response.toString(), AnswerResponse.class);
                        progressBar.setVisibility(View.GONE);
                        if (newAnswerResponse.getCode() == 1) {
                            getAnswers();
                            Toast.makeText(getContext(), getString(R.string.new_answer_added),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(tag,error.toString());
                Toast.makeText(getContext(), getString(R.string.error),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        queue.add(jsonObjectRequest);
    }

    private void getAnswers() {
        progressBar.setVisibility(View.VISIBLE);
        QuestionIDInput questionIDInput = new QuestionIDInput(mParam2,mParam3);
        JSONObject json = null;
        try {
            json = new JSONObject(gson.toJson(questionIDInput));;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getString(R.string.get_answers_question), json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(tag,response.toString());
                        AnswersListResponse answersResponse = gson.fromJson(response.toString(), AnswersListResponse.class);
                        if (answersResponse.getCode() == 1 && answersResponse.getData().size()>0) {
                            mAdapter = new MyAdapter(answersResponse.getData());
                            answer.setAdapter(mAdapter);
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), getString(R.string.error),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        queue.add(jsonObjectRequest);
    }
}

