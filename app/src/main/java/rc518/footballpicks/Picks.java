package rc518.footballpicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Picks extends AppCompatActivity {
private Spinner Spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picks);
        Spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adaper=new ArrayAdapter<String>(Picks.this,android.R.layout.simple_spinner_dropdown_item);
        


    }
}
