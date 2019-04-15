package com.example.edesia.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.foodsuggestorrecycler1.Model.RecipeModel;
import com.example.hp.foodsuggestorrecycler1.R;

import java.util.List;
class SearchViewHolder extends RecyclerView.ViewHolder
{

    public TextView title,prepTime,totalTime, ingredients,instructions;
    public ImageView picture;
    public SearchViewHolder(@NonNull View itemView)
    {
        super( itemView );
        title = itemView.findViewById( R.id.foodNameTv );
        //ingredients = itemView.findViewById( R.id.foodIngredientsTv );
        prepTime = itemView.findViewById( R.id.foodTasteTv );
        totalTime = itemView.findViewById( R.id.foodTypeTv);
        picture = itemView.findViewById(R.id.picture);

    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> implements  View.OnClickListener{

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
        View itemView = inflater.inflate( R.layout.layout_item, parent, false );
        return new SearchViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        holder.title.setText( recipeModels.get( position ).getTitle() );
        //holder.ingredients.setText( recipeModels.get( position ).getIngredients() );
        holder.prepTime.setText( recipeModels.get( position ).getPrepTime() );
        holder.totalTime.setText( recipeModels.get( position ).getTotalTime() );
        //holder.picture.setImageResource(position).getImageResource();
        // Glide.with(view.getContext()).load(url).into(picture);

    }

    @Override
    public int getItemCount() {
        return recipeModels.size();
    }
    public interface mClickListener {
        public void mClick(View v, int position);
    }
    @Override
    public void onClick(View v) {

    }
}
