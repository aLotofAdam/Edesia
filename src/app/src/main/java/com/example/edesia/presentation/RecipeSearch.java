package com.example.edesia.presentation;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

//import com.example.edesia.presentation.SearchAdapter;
//import com.example.edesia.presentation.Database;
//import com.example.edesia.;
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
    List<String> suggestList = new ArrayList<>(  );

    Database database;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.content_recipe_search);

        //initialize the view
        recyclerView = findViewById( R.id.mt_recycler_search );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );

        materialSearchBar = findViewById( R.id.search_bar );

        //initialize database
        database = new Database( this );

        //Setup search bar
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>(  );
                for (String search : suggestList)
                {
                    if (search.toLowerCase().contains( materialSearchBar.getText().toLowerCase() ))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        materialSearchBar.setOnSearchActionListener( new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {


                if (!enabled)
                {
                    //if close search, just restore default
                    adapter = new SearchAdapter(getApplicationContext(),database.getRecipeModel());
                    recyclerView.setAdapter( adapter );
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch( text.toString() );
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        } );


        adapter = new SearchAdapter( this, database.getRecipeModel() );
        recyclerView.setAdapter( adapter );
    }

    private void startSearch(String text) {

        adapter = new SearchAdapter( this,database.getRecipeByName( text ) );
        recyclerView.setAdapter( adapter );
    }


    private void loadSuggestList()
    {
        suggestList = database.getTitle();
        materialSearchBar.setLastSuggestions( suggestList );
    }

}