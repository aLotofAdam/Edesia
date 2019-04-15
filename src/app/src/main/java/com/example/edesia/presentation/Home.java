package com.example.edesia.presentation;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;

        import androidx.appcompat.app.AppCompatActivity;


public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button plan = findViewById(R.id.Plan);
        Button randomizer = findViewById(R.id.Randomizer);
        Button userMenu = findViewById(R.id.UserMenu);
        Button groceryList = findViewById(R.id.GroceryList);
        Button recipeSearch = findViewById(R.id.RecipeSearch);
        Button googleVision = findViewById(R.id.OCR);


        plan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMealPlanner();
            }
        });

        randomizer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRandomizer();
            }
        });

        userMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openUserMenu();
            }
        });

        groceryList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGroceryList();
            }
        });

        recipeSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRecipeSearch();
            }
        });

        googleVision.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGoogleVision();
            }
        });


    }

    public void openRandomizer(){
        Intent intent = new Intent(this, MealRandomizer.class);
        startActivity(intent);
    }

    public void openRecipeSearch(){
        Intent intent = new Intent(this, Recipe.class);
        startActivity(intent);
    }


    public void openMealPlanner(){
        Intent intent = new Intent(this, Plan.class);
        startActivity(intent);
    }


    public void openGroceryList(){
        Intent intent = new Intent(this, GroceryList.class);
        startActivity(intent);
    }


    public void openUserMenu(){
        Intent intent = new Intent(this, UserMenu.class);
        startActivity(intent);
    }


    public void openGoogleVision(){
        Intent intent = new Intent(this, GoogleVision.class);
        startActivity(intent);
    }
}
