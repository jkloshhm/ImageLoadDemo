package com.example.eventbus.lhdswipmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LHD on 2016/6/25.
 */
public class lhdswipadapter extends RecyclerView.Adapter<lhdswipadapter.ViewHolder> {

    private List<User> mItems = new ArrayList<User>();
    private Context mcontext;
    private OnlhdSwipItemClickListener listener;

    public lhdswipadapter(Context context, List<User> users, OnlhdSwipItemClickListener mlistner) {
        this.mcontext = context;
        this.mItems = users;
        this.listener = mlistner;
    }

    @Override
    public lhdswipadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.swipeitem, parent, false);
        ViewHolder vh = new ViewHolder(v, this.listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ViewHolder mholder = holder;
        User user = mItems.get(position);
        mholder.button.setText(user.name);
        mholder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "点击了第" + position + "个Button", Toast.LENGTH_SHORT).show();
            }
        });
        mholder.deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItems.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnlhdSwipItemClickListener mListener;
        private Button button;
        private Button deletebutton;

        public ViewHolder(View itemView, OnlhdSwipItemClickListener Listener) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.item_btn);
            deletebutton = (Button) itemView.findViewById(R.id.delete_button);
            this.mListener = Listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

}
