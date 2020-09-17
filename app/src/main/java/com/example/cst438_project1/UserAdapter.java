package com.example.cst438_project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class  UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    List<Course> courses;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public UserAdapter(List<Course> courses, OnClickListener clickListener){
        this.courses = courses;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflater to inflate a view
        View courseView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap it inside a view holder and return it
        return new UserHolder(courseView);
    }

    //binds the data to a particular viewholder
    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        //grab a Course at a current position
        Course currentCourse = courses.get(position);
//        holder.viewTitle.setText(currentCourse.getTitle());
        if(courses.size()>0) {
            holder.bind(currentCourse.getTitle());
        }else{
            holder.bind("No courses yett.");
        }
    }

    class UserHolder extends RecyclerView.ViewHolder{
//        private TextView view;
        private TextView viewTitle;
//        private TextView viewUser;

        public UserHolder(View userView){
            super(userView);
            viewTitle = userView.findViewById(R.id.viewTitle);
//            view = userView.findViewById(R.id.view);
//            viewUser = userView.findViewById(R.id.viewUser);
        }
        public void bind(String courseName){
            viewTitle.setText(courseName);
            viewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }

    //tells recycler how many items are in the list
    @Override
    public int getItemCount() {
        if(courses.size()<=0){
            return 0;
        }else {
            return courses.size();
        }
    }

    public void setUsers(List<Course> courses){
        this.courses = courses;
        notifyDataSetChanged();
    }
}
