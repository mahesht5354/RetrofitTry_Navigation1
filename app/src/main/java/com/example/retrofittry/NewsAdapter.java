package com.example.retrofittry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;

    public NewsAdapter(Context context, List<News> newsArticles) {
        this.context = context;
        this.newsArticles = newsArticles;
    }

    List<News> newsArticles;
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.one_item_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Glide.with(context).load(newsArticles.get(position).getImageUrl()).into(holder.news_image);
        holder.title.setText(newsArticles.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView news_image;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_news);
            news_image = itemView.findViewById(R.id.image_news);

            //itemView.setOnClickListener(this);

        }

        /*@Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            News newsData = newsArticles.get(position);
            Intent i = new Intent(context,DetailsActivity.class);
            i.putExtra("DETAILS", newsData);
            context.startActivity(i);

        }*/
    }
}
