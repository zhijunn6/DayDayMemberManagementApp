package com.example.chery.dayday;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Member> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // Passes data to constructor
    public SearchRecyclerViewAdapter(Context context, ArrayList<Member> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Inflates row layout from xml method
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.recycler_cardview, parent, false);
        return new ViewHolder(view);
    }

    // Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Member member = mData.get(position);

        holder.my_memberName.setText(member.getMemberName());
        holder.my_memberBirthMonth.setText(String.valueOf(member.getMemberBirthMonth()));
        holder.my_memberIC.setText(String.valueOf(member.getMemberIC()));
    }

    // Get umber of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Set the filter for query
    public void filterList(ArrayList<Member> filteredList){
        mData = filteredList;
        notifyDataSetChanged();
    }

    // Stores and recycles views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView my_memberName, my_memberBirthMonth, my_memberIC;
        CardView card;

        ViewHolder(View itemView){
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.member_cardView);
            my_memberName = (TextView) itemView.findViewById(R.id.display_memberName);
            my_memberBirthMonth = (TextView) itemView.findViewById(R.id.display_memberBirthMonth);
            my_memberIC = (TextView) itemView.findViewById(R.id.display_memberIC);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // Allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // Getting data at click position in RecyclerView
    Object getItem(int id) {
        return mData.get(id);
    }

    // Parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
