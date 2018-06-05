package com.android.teaching.miprimeraapp.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.android.teaching.miprimeraapp.R;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        myRecyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager myLayoutManager =
                new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(
                getResources().getStringArray(R.array.colors)
        );
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
    }
}
