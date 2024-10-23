package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageView im = findViewById(R.id.imageView2);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.my_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 创建歌曲列表
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("可惜不是你", "判词官", "p1"));
        songList.add(new Song("新专辑《UNDRESS》", "张杰", "p2"));
        songList.add(new Song("Espada carmesi", "杜宣达", "p3"));
        songList.add(new Song("电视剧《人民警察》原声专辑", "刘大拿", "p4"));
        songList.add(new Song("几分之几", "李若可小可爱", "p5"));
        songList.add(new Song("孤独患者", "何洁", "p6"));
        songList.add(new Song("蓝色的她秋冬巡演", "薛之谦", "p7"));

        // 创建适配器并设置给 RecyclerView
        SongAdapter adapter = new SongAdapter(songList, this);
        recyclerView.setAdapter(adapter);
    }
}