package com.example.edesia.presentation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Favorites extends AppCompatActivity{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>(  );
    DatabaseHelper myDb;
    Database database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        //initialize the view
        recyclerView = findViewById(R.id.mt_recycler_search_Favorites);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = findViewById(R.id.search_bar);

        //initialize database
        database = new Database(this);

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
                    adapter = new SearchAdapter( getBaseContext(),database.getRecipeModel());
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
        } );
        adapter = new SearchAdapter( this, database.getRecipeModel() );
        recyclerView.setAdapter(adapter);
    }

    private void startSearch(String text)
    {
        adapter = new SearchAdapter( this,database.getRecipeByName(text));
        recyclerView.setAdapter(adapter);
    }


    private void loadSuggestList()
    {
        suggestList = database.getTitle();
        materialSearchBar.setLastSuggestions(suggestList);
    }


}