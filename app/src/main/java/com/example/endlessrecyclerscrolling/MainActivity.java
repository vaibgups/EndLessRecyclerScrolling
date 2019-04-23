package com.example.endlessrecyclerscrolling;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EndLessAdapter endLessAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ImageView image;
    private String gifURL = "https://media.tenor.com/images/da3e418145fb17e705c57231775b29cb/tenor.gif";
    private boolean isScrolling = false;
    private int currentItem, totalItems, scrollOutItems;
    private ProgressBar progressBar;
    private boolean isFetchDataCall = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 1; i<=10 ; i++){
            arrayList.add(i);
        }

        init();

    }

    private void init() {
        recyclerView = findViewById(R.id.siteRecycler);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        endLessAdapter = new EndLessAdapter(this,arrayList);
        recyclerView.setAdapter(endLessAdapter);

     recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
             super.onScrollStateChanged(recyclerView, newState);
             if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                 isScrolling = true;
             }
         }

         @Override
         public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
             super.onScrolled(recyclerView, dx, dy);
             currentItem = linearLayoutManager.getChildCount();
             totalItems = linearLayoutManager.getItemCount();
             scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();
             if (isScrolling && (currentItem + scrollOutItems == totalItems)){
                 isScrolling = false;
                 if (!isFetchDataCall) {
                     fetchData();
                 }
             }
         }
     });

//        image = findViewById(R.id.image);

//        Glide.with(this).load(gifURL).into(image);

    }

    private void fetchData() {
        isFetchDataCall = true;
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i<=10 ; i++){
                    int lastIndex = arrayList.size()-1;
                    int lastIndexValue = arrayList.get(lastIndex)+1;

                    arrayList.add(arrayList.size(),lastIndexValue);

                }
                isFetchDataCall = false;
                progressBar.setVisibility(View.GONE);
                endLessAdapter.notifyDataSetChanged();
            }
        }, 5000);
    }
}
