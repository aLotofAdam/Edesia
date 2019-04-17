package com.example.edesia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edesia.Model.RecipeModel;

import com.example.edesia.RecipeSteps;
import com.example.hp.edesia.R;

import java.util.List;
class SearchViewHolder extends RecyclerView.ViewHolder
{

    public TextView title,prepTime,totalTime, ingredients,instructions;
    public ImageView picture;
    public Button addbutton;
    public SearchViewHolder(@NonNull View itemView)
    {
        super( itemView );
        title = itemView.findViewById( R.id.title );
        //ingredients = itemView.findViewById( R.id.foodIngredientsTv );
        prepTime = itemView.findViewById( R.id.prepTime );
        totalTime = itemView.findViewById( R.id.totalTime);
        picture = itemView.findViewById(R.id.picture);
        addbutton = (Button)itemView.findViewById(R.id.addbutton);


    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {



    private Context context;
    private List<RecipeModel> recipeModels;
    private View.OnClickListener mOnItemClickListener;


    public SearchAdapter(Context context, List<RecipeModel>recipeModels)
    {
        this.context = context;
        this.recipeModels = recipeModels;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View itemView = inflater.inflate( R.layout.content_recipe_overview, parent, false );
        return new SearchViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        holder.title.setText(recipeModels.get(position).getTitle());
        //holder.ingredients.setText( recipeModels.get( position ).getIngredients() );
        holder.prepTime.setText(recipeModels.get(position).getPrepTime());
        holder.totalTime.setText(recipeModels.get(position).getTotalTime());
        holder.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            // works
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeSteps.class);
                String title = recipeModels.get(position).getTitle();
                intent.putExtra("title", title );
                context.startActivity(intent);
            }
        });
       /*
        Glide.with(context)
                .asBitmap()
                .load(holder.picture)
                .into(holder.picture);
        holder.SearchViewHolder.setOnClickListener(new View.OnClickListener(){

            public void onClick (View view){
                Intent intent = new Intent(context, GalleryActivity.class);
                intent.putExtra("image_url", mImages.get(position));
                intent.putExtra("image_name", mImageNames.get(position));
                mContext.startActivity(intent);
            }
        }*/
    }

    @Override
    public int getItemCount() {
        return recipeModels.size();
    }

}
