package com.sharinggroup.task.ui.userslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.sharinggroup.task.AppConst;
import com.sharinggroup.task.R;
import com.sharinggroup.task.data.local.entity.UserEntity;
import com.sharinggroup.task.data.remote.model.Address;
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
        UserEntity user = users.get(position);

        holder.itemBinding.tvUserName.setText(user.getName());

        Address add = user.getAddress();
        String address = add.getStreet() + ", " + add.getSuite() + ", " + add.getCity();
        holder.itemBinding.tvAddress.setText(address);

        holder.itemBinding.tvPhone.setText(holder.itemView.getContext().getString(R.string.phone,
                users.get(position).getPhone()));

        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(v -> {

            int index = (int) v.getTag();
            Bundle arg = new Bundle();
            arg.putInt(AppConst.INTENT_USER_ID, users.get(index).getId());

            Navigation.findNavController(v).navigate(
                    R.id.action_usersListFragment_to_userProfileFragment, arg);
        });
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
