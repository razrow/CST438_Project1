package com.example.cst438_project1;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder>{
    public interface OnClickListener{
        void onItemClicked(int position);
    }

    List<Double> assingments;
    AssignmentAdapter.OnClickListener clickListener;

    public AssignmentAdapter(List<Double> assingments, AssignmentAdapter.OnClickListener clickListener) {
        this.assingments = assingments;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout to inflate a view
        View assignmentView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it inside viewholder and return it
        return new ViewHolder(assignmentView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.ViewHolder holder, int position) {
        //grab the item at position
        Double score = assingments.get(position);
        //bind item into viewholder
        holder.bind(score);
    }

    @Override
    public int getItemCount() {
        return assingments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAssignment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAssignment = itemView.findViewById(android.R.id.text1);
        }

        //update view inside of the viewholder with data
        public void bind (Double score) {
            double grade = score *100;
            tvAssignment.setText(Double.toString(grade));
            tvAssignment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }

    }
}
