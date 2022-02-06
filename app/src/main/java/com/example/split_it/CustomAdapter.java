package com.example.split_it;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.split_it.database.model.Group;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.GroupViewHolder> {
    private final Integer userId;
    private List<Group> groupList;

    public CustomAdapter(List<Group> groupList, Integer userId) {
        this.groupList = groupList;
        this.userId = userId;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new GroupViewHolder(rowItem, groupList, userId);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {

        Group currentGroup = groupList.get(position);
        holder.groupNameTextView.setText(currentGroup.getName());
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final List<Group> groupList;
        private final Integer userId;
        private TextView groupNameTextView;

        public GroupViewHolder(View view, List<Group> groupList, Integer userId) {
            super(view);
            view.setOnClickListener(this);
            this.groupNameTextView = view.findViewById(R.id.group_name_text_view);
            this.groupList = groupList;
            this.userId = userId;
        }

        @Override
        public void onClick(View view) {

            Context context = view.getContext();
            Group group = groupList.get(getLayoutPosition());

            Intent intent = new Intent(context, GroupActivity.class);
            intent.putExtra("groupId", group.getId());
            intent.putExtra("userId", userId);

            context.startActivity(intent);
        }
    }
}
