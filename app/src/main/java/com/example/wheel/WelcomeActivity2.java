package com.example.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity2 extends AppCompatActivity {
    private Button WelcomeDriverButton;
    private Button WelcomeCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        WelcomeCustomerButton = (Button) findViewById(R.id.welcome_customer_btn);
        WelcomeDriverButton = (Button) findViewById(R.id.welcome_driver_btn);

        WelcomeCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent LoginRegisterCustumerIntent = new Intent(WelcomeActivity2.this,CustomerLoginRegisterActivity.class);
                startActivity(LoginRegisterCustumerIntent);

            }
        });
        WelcomeDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent LoginRegisterCustumerIntent = new Intent(WelcomeActivity2.this,DriverLoginRegisterActivity.class);
                startActivity(LoginRegisterCustumerIntent);

            }
        });
    }
}