package com.example.musicapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.PlaylistViewHolder> {

    private List<User> playlistList;
    private Context context;
    private PlaylistDAO playlistDAO;

    public UserAdapter(List<User> playlistList,Context context) {
        this.playlistList = playlistList;
        this.context = context;
        this.playlistDAO = new PlaylistDAO(context);  // 初始化 DAO 用于删除操作
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        User playlist = playlistList.get(position);
        holder.nameTextView.setText(playlist.getName());
        holder.descriptionTextView.setText(playlist.getBio());

        // 设置点击事件用于删除项
        holder.itemView.setOnClickListener(v -> {
            // 弹出删除确认对话框
            new AlertDialog.Builder(context)
                    .setTitle("删除歌单")
                    .setMessage("确定要删除 " + playlist.getName() + " 吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        // 从数据库中删除该项
                        playlistDAO.deletePlaylist(playlist.getName());

                        // 从列表中删除该项
                        playlistList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, playlistList.size());

                        Toast.makeText(context, "已删除歌单", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("取消", null)
                    .create().show();
        });

        // 修改按钮点击事件
        holder.editButton.setOnClickListener(v -> {
            // 弹出修改对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_edit_playlist, null);
            builder.setView(dialogView);

            // 获取输入框
            final EditText editName = dialogView.findViewById(R.id.edit_name);
            final EditText editBio = dialogView.findViewById(R.id.edit_bio);

            // 预填充当前的歌单名称和简介
            editName.setText(playlist.getName());
            editBio.setText(playlist.getBio());

            // 设置对话框按钮
            builder.setTitle("修改歌单")
                    .setPositiveButton("确定", (dialog, which) -> {
                        String newName = editName.getText().toString();
                        String newBio = editBio.getText().toString();

                        // 更新数据库
                        playlistDAO.updatePlaylist(playlist.getName(), newName, newBio);

                        // 更新列表
                        playlist.setName(newName);
                        playlist.setBio(newBio);
                        notifyItemChanged(position);

                        Toast.makeText(context, "歌单已修改", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("取消", null)
                    .create().show();
        });
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView descriptionTextView;

        public TextView editButton;
        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            descriptionTextView = itemView.findViewById(R.id.bio);
            editButton = itemView.findViewById(R.id.edit_button);
        }
    }
}
