 package com.example.wheel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

 public class CustomerLoginRegisterActivity extends AppCompatActivity
{
   private Button CustomerLoginButton;
   private Button CustomerRegisterButton;
   private TextView CustomerRegisterLink;
   private TextView CustumerStatus;
    private EditText EmailCustomer;
    private EditText PasswordCustomer;
    private ProgressDialog LOadingBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        mAuth = FirebaseAuth.getInstance();

        CustomerLoginButton = (Button) findViewById(R.id.customer_login_btn);
        CustomerRegisterButton =(Button) findViewById(R.id.customer_register_btn);
        CustomerRegisterLink = (TextView) findViewById(R.id.register_customer_link);
        CustumerStatus =(TextView) findViewById(R.id.customer_status);
        EmailCustomer =(EditText) findViewById(R.id.email_customer);
        PasswordCustomer =(EditText) findViewById(R.id.password_customer);
        LOadingBar = new ProgressDialog(this);

        CustomerRegisterButton.setVisibility(View.INVISIBLE);
        CustomerRegisterButton.setEnabled(false);

        CustomerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               CustomerLoginButton.setVisibility(View.INVISIBLE);
               CustomerRegisterLink.setVisibility(View.INVISIBLE);
               CustumerStatus.setText("Register Customer");

               CustomerRegisterButton.setVisibility(View.VISIBLE);
               CustomerRegisterButton.setEnabled(true);
            }
        });

        CustomerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email= EmailCustomer.getText().toString();
                String password = PasswordCustomer.getText().toString();

                RegisterCustomer(email,password);

            }
        });
    }

    private void RegisterCustomer(String email, String password)
    {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            LOadingBar.setTitle("Customer Registration");
            LOadingBar.setMessage("Please wait, While we are register your data...");
            LOadingBar.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Register Successfully ", Toast.LENGTH_SHORT).show();
                                LOadingBar.dismiss();
                            }
                            else {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Registertion Unsuccessful,Please Try Again ....", Toast.LENGTH_SHORT).show();
                                LOadingBar.dismiss();
                            }
                        }
                    });
        }
    }
}