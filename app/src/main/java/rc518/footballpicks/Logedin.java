package rc518.footballpicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Logedin extends AppCompatActivity {
private Button GroupB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logedin);
   GroupB=(Button)findViewById(R.id.GROUPS);
        GroupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Logedin.this,Group1.class);
                startActivity(i);
            }
        });
    }
}
