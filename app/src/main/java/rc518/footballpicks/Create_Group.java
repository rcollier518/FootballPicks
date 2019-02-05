package rc518.footballpicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create_Group extends AppCompatActivity {
private Button Sub;
    private EditText Name;
    private  EditText Password;
    private EditText UserName;
    private String GroupN;
    private  String GroupPass;
    private String UserN;
    private FirebaseDatabase database;
    private FirebaseUser userN;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__group);
        Sub=(Button)findViewById(R.id.SUB);
        mAuth = FirebaseAuth.getInstance();
        Name=(EditText)findViewById(R.id.Gname);
        UserName=(EditText)findViewById(R.id.UserName);

        Password=(EditText)findViewById(R.id.Gpass);
        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();

                Toast.makeText(Create_Group.this, GroupN, Toast.LENGTH_SHORT).show();
                int i=(check(Name.getText().toString(),Password.getText().toString(),UserName.getText().toString()));
                if(i==1){
                    GroupN=Name.getText().toString();

                    GroupPass=Password.getText().toString();
                    DatabaseReference myRef = database.getReference("GROUPS").child("GROUP").child(GroupN);
                    myRef.child("Group Pass").setValue(GroupPass);
                    myRef.child("Admin").setValue(UserName.getText().toString());
                    DatabaseReference myRef2 = database.getReference("GROUPS").child("GROUP").child(GroupN).child("Members").child(UserName.getText().toString());
                    myRef2.child("Week1").setValue("N/A");
myRef2.child("Week2").setValue("N/A");
 myRef2.child("Week3").setValue("N/A");
myRef2.child("Week4").setValue("N/A");
                    myRef2.child("Week5").setValue("N/A");
                  myRef2.child("Week6").setValue("N/A");




            }
        };
    });
}
public int check(String GN,String GP,String UserName) {
    if (GN.equals("")) {
        Toast.makeText(this, "Please enter a group name", Toast.LENGTH_SHORT).show();
        return 0;
    } else if (GP.equals("")) {
        Toast.makeText(this, "Please enter a group password", Toast.LENGTH_SHORT).show();
        return 0;
    } else if (UserName.equals("")) {
        Toast.makeText(this, "Please enter a userName", Toast.LENGTH_SHORT).show();
        return 0;
    } else {
        return 1;
    }
}
}
