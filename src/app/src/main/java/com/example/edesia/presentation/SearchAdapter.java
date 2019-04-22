package com.example.edesia.presentation;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title,prepTime,totalTime, ingredients,instructions;
    public ImageView picture;
    public Button addbutton,favorite;


    public SearchViewHolder(@NonNull View itemView)
    {
        super( itemView );
        title = itemView.findViewById( R.id.title );
        prepTime = itemView.findViewById( R.id.prepTime );
        totalTime = itemView.findViewById( R.id.totalTime);
        picture = (ImageView)itemView.findViewById(R.id.picture);
        addbutton = (Button)itemView.findViewById(R.id.addbutton);



    }

    @Override
    public void onClick(View v) {

    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<RecipeModel> recipeModels;


    public SearchAdapter(Context context, List<RecipeModel>recipeModels)
    {
        this.context = context;
        this.recipeModels = recipeModels;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View itemView = inflater.inflate(R.layout.content_recipe_overview, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int position) {

        holder.title.setText(recipeModels.get(position).getTitle());
        holder.prepTime.setText(recipeModels.get(position).getPrepTime());
        holder.totalTime.setText(recipeModels.get(position).getTotalTime());
        String url = recipeModels.get(position).getPicture();
        //not working properly

        Glide.with(context).load(url).into(holder.picture);


        holder.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(context,RecipeSteps.class);
                String title = recipeModels.get(position).getTitle();
                int id = recipeModels.get(position).getID();
                String ingredients = recipeModels.get(position).getIngredients();
                String instructions = recipeModels.get(position).getInstructions();
                String url = recipeModels.get(position).getPicture();

                intent.putExtra("title", title );
                intent.putExtra("id", id);
                intent.putExtra("ingredients", ingredients );
                intent.putExtra("instructions", instructions);
                context.startActivity(intent);
            }
        });

        /* image creation
        Glide.with(context)
                .asBitmap()
                .load(holder.picture)
                .into(holder.picture);
        holder.SearchViewHolder.setOnClickListener(new View.OnClickListener(){
*/
    }
    @Override
    public int getItemCount() {
        return recipeModels.size();
    }


}

