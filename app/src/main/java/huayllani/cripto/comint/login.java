package huayllani.cripto.comint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class login extends AppCompatActivity {
    public EditText editTextUser;
    public EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = (EditText) findViewById(R.id.et_user);
        editTextPassword = (EditText) findViewById(R.id.et_password);
    }

    public void signin(View view){
        Intent login = new Intent(this, menu.class);
        DataBase db = new DataBase();
        ArrayList<String[]> list = db.getUser();
        if (!list.isEmpty()) {
            for (String[] row: list) {
                String aux1 = "";
                for (String column: row) {
                    aux1 += column + " | ";
                }
                Toast.makeText(login.this, aux1, Toast.LENGTH_LONG).show();
            }
            startActivity(login);
        } else {
            Toast.makeText(login.this, "sin datos", Toast.LENGTH_LONG).show();
        }
    }
}
