package com.example.cst438_project1;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.List;

//this is where we take in data and then display it
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    List<String> courses;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public CourseAdapter(List<String> courses, OnLongClickListener onLongClickListener, OnClickListener clickListener) {
        this.courses = courses;
        this.longClickListener = onLongClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout to inflate a view
        View courseView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it inside viewholder and return it
        return new ViewHolder(courseView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //grab the item at position
        String course = courses.get(position);
        //bind item into viewholder
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCourse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourse = itemView.findViewById(android.R.id.text1);
        }

        //update view inside of the viewholder with data
        public void bind(String course) {
            tvCourse.setText(course);
            tvCourse.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });

            tvCourse.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return false;
                }
            });
        }

    }
}
