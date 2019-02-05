package rc518.footballpicks;

import android.app.Dialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;


public class Login extends AppCompatActivity {
    private Button Login_B;
    private Button Signup_B;
    private String user;
    private String Password;
    private EditText email;
    private EditText Pass;
    private FirebaseAuth mAuth;
    private String Test;
    private FirebaseUser userN;
    private   FirebaseDatabase database;
    private FirebaseAuth.AuthStateListener mAuthListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login_B = (Button) findViewById(R.id.Login_B);
        Signup_B = (Button) findViewById(R.id.Sign_UpB);
        email = (EditText) findViewById(R.id.Email);
        Pass = (EditText) findViewById(R.id.Pass);
// ...
        mAuth = FirebaseAuth.getInstance();
        Login_B.findViewById(R.id.Login_B);
Login_B.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (email.getText().toString().equals("")) {
            Toast.makeText(Login.this, "Please Type your email", Toast.LENGTH_SHORT).show();
        }
        if (Pass.getText().toString().equals("")) {
            Toast.makeText(Login.this, "Please Type your password", Toast.LENGTH_SHORT).show();

        }

        if (email.getText().toString() != null) {
            user = email.getText().toString();
        }
        if (Pass.getText().toString() != null) {
            Password = Pass.getText().toString();

        }
SignIn(user,Password);
    }
});
        Signup_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Button Pressed", Toast.LENGTH_SHORT).show();


                if (email.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Please Type your email", Toast.LENGTH_SHORT).show();

                }
                if (Pass.getText().toString() == null) {
                    Toast.makeText(Login.this, "Please Type your password", Toast.LENGTH_SHORT).show();

                }

                if (email.getText().toString() != null) {
                    user = email.getText().toString();
                }
                if (Pass.getText().toString() != null) {
                    Password = Pass.getText().toString();

                }
                CreateAccount(user, Password);


            }

            ;
        });
    }

    public void CreateAccount(String User, String Password) {
        Toast.makeText(this, "HERE", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(User, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                   userN=mAuth.getCurrentUser();
                    userN.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "Email Verification sent", Toast.LENGTH_LONG).show();


                                Toast.makeText(Login.this, "DONE", Toast.LENGTH_SHORT).show();








                            }
                        }
                    });

                    Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Fail", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public void SignIn(String user,String Pass) {
        database = FirebaseDatabase.getInstance();

        mAuth.signInWithEmailAndPassword(user, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {


                   userN = FirebaseAuth.getInstance().getCurrentUser();

                    if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {


                            DatabaseReference myRef = database.getReference("USERS").child("USER").child(userN.getUid()).child("TEST");
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    try {
                                        Toast.makeText(Login.this, "ON HERE", Toast.LENGTH_SHORT).show();
                                      Object obj = dataSnapshot.getValue();
                                        Toast.makeText(Login.this, obj.toString(), Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(Login.this,Logedin.class);
                                        startActivity(i);
                                    }
                                    catch (Exception e) {
                                        DatabaseReference myRef = database.getReference("USERS").child("USER").child(userN.getUid());
                                        myRef.child("Email").setValue(userN.getEmail());
                                        myRef.child("IN?").setValue("Y/N");
                                        myRef.child("GROUP").setValue("N/A");
                                        myRef.child("ADMIN FOR GROUP?").setValue("N");
                                        myRef.child("TEST").setValue("1");


                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                     else {
                        Toast.makeText(Login.this, "Please verfiy your email", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(Login.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
