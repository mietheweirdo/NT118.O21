package com.example.thuchanh2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Bai6 extends AppCompatActivity {
    private ArrayList<Hero> mHeros;
    private RecyclerView mRecyclerHero;
    private HeroAdapter mHeroAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai6);
        mRecyclerHero = findViewById(R.id.recycleHero);
        mHeros = new ArrayList<>();
        createHeroList();
        mHeroAdapter = new HeroAdapter(this, mHeros);
        mRecyclerHero.setAdapter(mHeroAdapter);
        mRecyclerHero.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerHero.setHasFixedSize(true);
//        mRecyclerHero.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void createHeroList() {
        mHeros.add(new Hero("Thor", R.drawable.thor));
        mHeros.add(new Hero("ironMan", R.drawable.ironman));
        mHeros.add(new Hero("SpiderMan", R.drawable.spider));
        mHeros.add(new Hero("Captain America", R.drawable.captain));
        mHeros.add(new Hero("Thor", R.drawable.thor));
        mHeros.add(new Hero("ironMan", R.drawable.ironman));
        mHeros.add(new Hero("SpiderMan", R.drawable.spider));
        mHeros.add(new Hero("Captain America", R.drawable.captain));
        mHeros.add(new Hero("Thor", R.drawable.thor));
        mHeros.add(new Hero("ironMan", R.drawable.ironman));
        mHeros.add(new Hero("SpiderMan", R.drawable.spider));
        mHeros.add(new Hero("Captain America", R.drawable.captain));
    }
}