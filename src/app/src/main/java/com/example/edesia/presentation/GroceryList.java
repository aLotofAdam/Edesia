package com.example.edesia.presentation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import androidx.appcompat.app.AppCompatActivity;

public class GroceryList extends AppCompatActivity {
    List<String>ingredientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);

        //initialize the database
        Database db = new Database(this);

       ingredientList = db.getIngredients();
       System.out.println(ingredientList);

        ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;
        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        Spinner s6 = findViewById(R.id.month_spinner3);
        Spinner s7 = findViewById(R.id.day_spinner3);

        months = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                monthArr);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s6.setAdapter(months);



        days = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                dayArr);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s7.setAdapter(days);


    }

    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "databases/recipes.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }



    /*//function to get all ingredient names
    public List<String> getIngredients()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String select = "MIN(id) AS id, Ingredients";
        String order = "ascending";

        String[] sqlSelect = {"Ingredients"};
        String tableName = "recipes";  //table name

        //all column names with no duplicates in ascending order
        qb.setTables( tableName );
        Cursor cursor = qb.query(db, sqlSelect,select,null,null,null,order);
        List<String>result = new ArrayList<>(  );
        if (cursor.moveToFirst())
        {
            do{
                result.add( cursor.getString( cursor.getColumnIndex( "Ingredients" ) ));
            }while (cursor.moveToNext());

        }
        return result;
    }

    *//**
     * select all rows in the warehouses table
     *//*
    public void selectAll(){
        String sql = "SELECT id, name, capacity FROM warehouses";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
}

/*TODO previous life as a fragment below
public class GroceryList extends Fragment {
    private OnFragmentInteractionListener mListener;

    public GroceryList() {
        // Required empty public constructor
    }

    public static void setOnClickListener(View.OnClickListener onClickListener) {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate layout
        return inflater.inflate(R.layout.grocery_list, container, false);
    }

   *//* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // Button button = view.findViewById(R.id.gl_to_home);
        //button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.gl_to_home, null));
        //uses navigationController to use the action gl_to_home button to navigate
        //Navigation.findNavController(view).navigate(R.id.action_grocery_list_to_home);

        view.findViewById(R.id.grocery_list).setOnClickListener(Navigation.
                createNavigateOnClickListener(R.id.action_grocery_list_to_home, savedInstanceState));
    }*//*

    //allows communication between fragment and activity
    public interface OnFragmentInteractionListener {
    }
}*/
