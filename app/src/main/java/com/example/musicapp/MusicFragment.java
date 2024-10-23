package com.example.musicapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.PlaylistDAO;
import com.example.musicapp.User;
import com.example.musicapp.UserAdapter;

import java.util.List;

public class MusicFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter playlistAdapter;
    private List<User> playlistList;
    private PlaylistDAO playlistDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 初始化数据库操作类
        playlistDAO = new PlaylistDAO(getContext());

        // 从数据库中获取现有的歌单数据
        playlistList = playlistDAO.getAllPlaylists();

        // 设置适配器
        playlistAdapter = new UserAdapter(playlistList,getContext());
        recyclerView.setAdapter(playlistAdapter);

        // 添加点击事件，弹出输入对话框
        View addLayout = view.findViewById(R.id.add);
        addLayout.setOnClickListener(v -> showAddDialog());

        return view;
    }

    // 显示添加歌单的对话框
    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("新建歌单");

        // 自定义对话框布局
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_playlist, null);
        builder.setView(dialogView);

        EditText nameEditText = dialogView.findViewById(R.id.editTextName);
        EditText descriptionEditText = dialogView.findViewById(R.id.editTextDescription);

        builder.setPositiveButton("保存", (dialog, which) -> {
            String name = nameEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description)) {
                Toast.makeText(getContext(), "歌单名称和简介不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            // 插入数据库并刷新 RecyclerView
            playlistDAO.insertPlaylist(name, description);
            playlistList.add(new User(name, description));  // 这里 id 可以忽略，或者再查询数据库更新
            playlistAdapter.notifyItemInserted(playlistList.size() - 1);
        });

        builder.setNegativeButton("取消", null);
        builder.create().show();
    }
}
