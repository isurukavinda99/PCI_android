package com.example.procurementconstructionindustry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.procurementconstructionindustry.database.DatabaseHelper;
import com.example.procurementconstructionindustry.database.DatabaseTable;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);
    }

    public void login(View v){

        EditText userName = findViewById(R.id.txt_login_user_name);
        EditText password = findViewById(R.id.txt_login_password);

        if(userName.getText().length() != 0){

            if(password.getText().length() != 0){

                String cols[] = {"*"};
                String user_where = DatabaseTable.User.USER_NAME + " = ? and " + DatabaseTable.User.USER_PASSWORD + " = ? and " + DatabaseTable.User.USER_LEVEL + " = ? ";
                String whereArgs[] = {userName.getText().toString() , password.getText().toString() , "3"};
                Cursor user = mydb.view(
                        DatabaseTable.User.TABLE_NAME,
                        cols,
                        user_where,
                        whereArgs,
                        null
                );

                if(user.getCount() == 1){

                    while (user.moveToNext()) {
                        Const.user_name = user.getString(user.getColumnIndexOrThrow(DatabaseTable.User.USER_NAME));
                        Const.user_id = Integer.parseInt(user.getString(user.getColumnIndexOrThrow(DatabaseTable.User.USER_ID)));
                    }

                    Intent dashboard = new Intent(this , Dashboard.class);
                    startActivity(dashboard);

                }else{
                    Toast patient_error = new Toast(this);
                    patient_error.setText("User not found");
                    patient_error.show();
                }

            }else{
                password.setError("Password is required");
            }

        }else{
            userName.setError("User name is required");
        }

    }
}