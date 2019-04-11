package com.example.edesia.presentation;
//imports needed
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class RecipeSearch extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    MaterialSearchBar materialSearchBar;
    List<String>  suggestList = new ArrayList<>();
    Database database;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipe_search);
        recyclerView = (RecyclerView)findViewById(R.id.recipe_Search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_Bar);

    database = new Database(this);
    materialSearchBar.setHint("Search for Recipe");
    materialSearchBar.setCardViewElevation(10);
    loadSuggestList();
    materialSearchBar.addTextChangeListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            List<String> suggest = new ArrayList<>();
            for (String search : suggestList) {
                if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())) {
                    suggest.add(search);

                }
                materialSearchBar.setLastSuggestions(suggest);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });


    materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
        @Override
        public void onSearchStateChanged(boolean enabled) {
            if(!enabled){
                recyclerView.setAdapter(adapter);
            }

        }

        @Override
        public void onSearchConfirmed(CharSequence text) {
            startSearch(text.toString());
        }


        @Override
        public void onButtonClicked(int buttonCode) {

        }
    });
    adapter = new SearchAdapter(this,database.getRecipes());
    }

    private void loadSuggestList() {
        suggestList = database.getTitles();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    private void startSearch(String text) {
        adapter = new SearchAdapter(this, database.getRecipebyName(text));
        recyclerView.setAdapter(adapter);
    }
}


