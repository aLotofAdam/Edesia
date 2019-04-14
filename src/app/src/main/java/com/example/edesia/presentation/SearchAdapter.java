package com.example.edesia.presentation;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView recipe;

    public void setTitle(TextView title) {
        this.title = title;
    }

    public void setPrepTime(TextView prepTime) {
        this.prepTime = prepTime;
    }

    public void setTotalTime(TextView totalTime) {
        this.totalTime = totalTime;
    }

    public TextView title;
    public TextView prepTime;
    public TextView totalTime;
    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        title=(TextView)itemView.findViewById(R.id.title);
        prepTime=(TextView)itemView.findViewById(R.id.prepTime);
        totalTime=(TextView)itemView.findViewById(R.id.totalTime);



    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{
        private Context context;
        private List<Recipe> recipes;

        public SearchAdapter(Context context, List<Recipe> recipes) {
            this.context = context;
            this.recipes = recipes;
        }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView =  inflater.inflate(R.layout.content_recipe_overview,parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.title. setText(recipes.get(position).getTitle());
        holder.prepTime. setText(recipes.get(position).getPrepTime());
        holder.totalTime. setText(recipes.get(position).getTotalTime());

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
