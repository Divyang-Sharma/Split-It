package com.example.split_it;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.split_it.database.model.Group;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.GroupViewHolder> {
    private List<Group> groupList;

    public CustomAdapter (List<Group> groupList){
       this.groupList = groupList;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new GroupViewHolder(rowItem, groupList);
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
        private TextView groupNameTextView;

        public GroupViewHolder(View view, List<Group> groupList) {
            super(view);
            view.setOnClickListener(this);
            this.groupNameTextView = view.findViewById(R.id.group_name_text_view);
            this.groupList = groupList;
        }

        @Override
        public void onClick(View view) {

            Context context = view.getContext();
            Group group = groupList.get(getLayoutPosition());

            Intent intent = new Intent(context,GroupActivity.class);
            intent.putExtra("groupId",group.getId());

            context.startActivity(intent);
        }
    }
}
