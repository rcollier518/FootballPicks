package rc518.footballpicks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Group1 extends AppCompatActivity {
private Button create;
    private Button Join_GB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group1);
        Join_GB=(Button)findViewById(R.id.Join_G);
        Join_GB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Group1.this,Join_Group.class);
                startActivity(i);
            }
        });
        create=(Button)findViewById(R.id.CreateB);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Group1.this,Create_Group.class);
                startActivity(i);

            }
        });
    }
}
