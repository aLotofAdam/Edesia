package com.example.edesia.presentation;

        import android.os.Bundle;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;

        import androidx.appcompat.app.AppCompatActivity;


public class Home extends AppCompatActivity {


    String selectedMonth;
    int selectedDay;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);

        ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;
        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};

        Spinner s1 = findViewById(R.id.month_spinner1);
        Spinner s2 = findViewById(R.id.day_spinner1);

        months = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                monthArr);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(months);



        days = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                dayArr);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(days);

        selectedMonth = s1.getSelectedItem().toString();
        selectedDay = Integer.parseInt(s2.getSelectedItem().toString());

    }
}
