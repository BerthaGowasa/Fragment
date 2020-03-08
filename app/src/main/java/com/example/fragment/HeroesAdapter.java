package com.example.fragment;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HeroesModel> heroList;

    public HeroesAdapter(Context context, ArrayList<HeroesModel> heroList) {
        this.context = context;
        this.heroList = heroList;
    }

    public ArrayList<HeroesModel> getHeroList() {
        return heroList;
    }

    public void setHeroList(ArrayList<HeroesModel> heroList) {
        this.heroList = heroList;
    }

    //2
    @NonNull
    @Override
    public HeroesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listpahlawan, viewGroup, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesAdapter.ViewHolder viewHolder, final int position) {
        Glide.with(context).load(getHeroList().get(position).getHeroImage()).into(viewHolder.imageView);
        viewHolder.tvnama.setText(getHeroList().get(position).getHeroName());
        viewHolder.btnLihat.setText(getHeroList().get(position).getHeroDetail());
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Ini Pahlawan  : " + getHeroList().get(position).getHeroName(), Toast.LENGTH_SHORT).show();
            }

        });
        viewHolder.btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPahlawan.class);
                intent.putExtra("img_url", getHeroList().get(position).getHeroImage());
                intent.putExtra("name", getHeroList().get(position).getHeroName());
                intent.putExtra("detail", getHeroList().get(position).getHeroDetail());
                context.startActivity(intent);
            }

        });
        viewHolder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String heroesDetails = "saya memberi kamu informasi tentang PAHLAWAN DI INDONESIA \n Nama Pahlawan : " + getHeroList().get(position).getHeroName() + "\n" + getHeroList().get(position).getHeroDetail();
                intent.putExtra(Intent.EXTRA_TEXT, heroesDetails);
                context.startActivity(Intent.createChooser(intent, "Share To "));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getHeroList().size();
    }

    //3
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvnama;
        private Button btnShare;
        private Button btnLihat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hero_thumbnail);
            tvnama = itemView.findViewById(R.id.hero_name);
            btnShare = itemView.findViewById(R.id.btn_share);
            btnLihat = itemView.findViewById(R.id.btn_lihat);


        }
    }

}
