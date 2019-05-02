package huayllani.cripto.comint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public EditText editTextUser;
    public EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = (EditText) findViewById(R.id.et_user);
        editTextPassword = (EditText) findViewById(R.id.et_password);
    }



    public void signin(View view){
        Intent Menu = new Intent(this, menu.class);
        DataBase db = new DataBase();
        if (db.isLogin(editTextUser.getText().toString(), editTextPassword.getText().toString())) {
            SharedPreferences.Editor cache = getSharedPreferences("COMINT", MODE_PRIVATE).edit();
            cache.putString("username", editTextUser.getText().toString());
            cache.putString("password", editTextPassword.getText().toString());
            cache.apply();
            startActivity(Menu);
        } else {
            Toast.makeText(this, "Usuario y/o Contraseña incorrectos", Toast.LENGTH_LONG).show();
        }
    }
}
