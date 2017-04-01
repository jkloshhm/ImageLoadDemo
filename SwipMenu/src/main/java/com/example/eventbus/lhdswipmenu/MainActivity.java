package com.example.eventbus.lhdswipmenu;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<User> users = new ArrayList<User>();
    private OnlhdSwipItemClickListener mlistener;
    private lhdswipadapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;
private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initswiprefresh();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.listview);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        initdata();
        recyclerView.setAdapter(adapter);
    }

    private void initdata() {
        users.add(new User("1"));
        users.add(new User("2"));
        users.add(new User("3"));
        users.add(new User("4"));
        users.add(new User("5"));
        users.add(new User("6"));
        users.add(new User("7"));
        users.add(new User("8"));
        users.add(new User("9"));
        adapter = new lhdswipadapter(MainActivity.this, users, new OnlhdSwipItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击了第" + position + "个View", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initswiprefresh() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiprefresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                users.add(new User("a"));
                users.add(new User("b"));
                users.add(new User("c"));
                users.add(new User("d"));
                users.add(new User("e"));
                swipeRefreshLayout.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
