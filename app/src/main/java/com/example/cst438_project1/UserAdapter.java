package com.example.cst438_project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{
    private List<User> users = new ArrayList<>();

    class UserHolder extends RecyclerView.ViewHolder{
        private TextView view;
        private TextView viewTitle;
        private TextView viewUser;

        public UserHolder(View userView){
            super(userView);
            viewTitle = userView.findViewById(R.id.viewTitle);
            view = userView.findViewById(R.id.view);
            viewUser = userView.findViewById(R.id.viewUser);
        }
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user, parent, false);
        return new UserHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User currentUser = users.get(position);
        holder.viewTitle.setText(currentUser.getUsername());
        holder.view.setText(currentUser.getFName());
        holder.viewUser.setText(currentUser.getFName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }
}
