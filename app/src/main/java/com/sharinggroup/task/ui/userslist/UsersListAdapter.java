package com.sharinggroup.task.ui.userslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sharinggroup.task.data.local.entity.UserEntity;
import com.sharinggroup.task.databinding.UsersListItemBinding;
import java.util.ArrayList;
import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.CustomViewHolder> {

    private List<UserEntity> users;

    public UsersListAdapter() {
        this.users = new ArrayList<>();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UsersListItemBinding itemBinding = UsersListItemBinding.inflate(
                layoutInflater, parent, false);
        return new CustomViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.itemBinding.message.setText(users.get(position).getName());
    }

    public void setItems(List<UserEntity> users) {
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    public List<UserEntity> getItems() {
        return users;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    protected class CustomViewHolder extends RecyclerView.ViewHolder {

        private UsersListItemBinding itemBinding;
        public CustomViewHolder(UsersListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

        }
    }
}
