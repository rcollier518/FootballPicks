package rc518.footballpicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Join_Group extends AppCompatActivity {
    private EditText GroupName;
    private EditText Password;
    private EditText UserName;
    private Button Join;
    private String UserN;
    private int che = 6;
    private int i;


    private FirebaseUser userName;
    private String Pass;
    private String GroupN;
    private FirebaseDatabase database;
    private FirebaseUser userN;
    private FirebaseAuth mAuth;
    private String groupNam;
    private String groupPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = 50;
        setContentView(R.layout.activity_join__group);
        mAuth = FirebaseAuth.getInstance();

        GroupName = (EditText) findViewById(R.id.groupNsmr);
        Password = (EditText) findViewById(R.id.GP);
        UserName = (EditText) findViewById(R.id.UN);
        Join = (Button) findViewById(R.id.Join_B);
        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check(GroupName.getText().toString(), Password.getText().toString(), UserName.getText().toString()) == 1) {

                } else {
                    database = FirebaseDatabase.getInstance();

                    UserN = UserName.getText().toString();
                    Pass = Password.getText().toString();
                    GroupN = GroupName.getText().toString();
                    DatabaseReference myRef = database.getReference("GROUPS").child("GROUP").child(GroupN);
               myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       if(dataSnapshot.exists()){
                           String name=dataSnapshot.getValue().toString();
                           DatabaseReference myRef = database.getReference("GROUPS").child("GROUP").child(GroupN).child("Group Pass");
                           myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {
                                   String password=dataSnapshot.getValue().toString();
                                   if(password.equals(Pass)){
                                       DatabaseReference myRef = database.getReference("GROUPS").child("GROUP").child(GroupN).child("Members").child(UserN);
                                       myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                               if(dataSnapshot.exists()){
                                                   Toast.makeText(Join_Group.this, "Username already exists", Toast.LENGTH_SHORT).show();
                                               }
                                               else{
                                                   DatabaseReference myRef = database.getReference("GROUPS").child("GROUP").child(GroupN).child("Members").child(UserN);
                                                   myRef.child("Week1").setValue("N/A");
                                                   myRef.child("Week2").setValue("N/A");
                                                   myRef.child("Week3").setValue("N/A");
                                                   myRef.child("Week4").setValue("N/A");
                                                   myRef.child("Week5").setValue("N/A");
                                                   myRef.child("Week6").setValue("N/A");
                                                   userName=mAuth.getCurrentUser();
                                                   DatabaseReference myeRef = database.getReference("USERS").child("USER").child(userName.getUid());
                                                   myeRef.child("GROUP").setValue(GroupN);
                                                   myeRef.child("IN?").setValue("Y");

                                               }
                                           }

                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {

                                           }
                                       });



                                   }
                                   else{
                                       Toast.makeText(Join_Group.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                   }
                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });


                       }
                       else{
                           Toast.makeText(Join_Group.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
                }

            }
        });
    }


    public int check(String GroupN, String Pass, String UserN) {

        if (GroupN.equals("")) {
            return 1;
        } else if (Pass.equals("")) {
            return 1;
        } else if (UserN.equals("")) {
            return 1;
        } else {
            return 0;
        }
    }
}









