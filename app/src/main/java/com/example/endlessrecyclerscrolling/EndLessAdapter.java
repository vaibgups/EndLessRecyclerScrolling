package com.example.endlessrecyclerscrolling;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EndLessAdapter extends RecyclerView.Adapter<EndLessAdapter.MyViewHolder> {


    private Context context;
    private ArrayList<Integer> arrayList;

    public EndLessAdapter(Context context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public EndLessAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_content,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EndLessAdapter.MyViewHolder viewHolder, int i) {

        int number = arrayList.get(i);
        viewHolder.setTextView(number);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public void setTextView(int num){
            textView.setText(""+num);
        }
    }
}
