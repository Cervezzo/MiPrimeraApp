package tarkov.android.teaching.com.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RelativeL extends AppCompatActivity {


    public void save(View view){
        Intent intent = new Intent(this, ProfileActivity.class );
        startActivity(intent);
    }

    public void delete(View view){
        Intent intent = new Intent(this, ProfileActivity.class );
        startActivity(intent);
    }

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        emailEditText = findViewById(R.id.email);
    }

    public void guardarDatos(View view){
        Log.d("ProfileActivity", "Username: " + usernameEditText.getText());
        //NO SE DEBE DE GUARDAR CONTRASEÑA EN LA VIDA REAL, ES SOLO PARA QUE LO VEAMOS
        Log.d("ProfileActivity", "Password: " + passwordEditText.getText());
        Log.d("ProfileActivity", "Email: " + emailEditText.getText());
        Log.d("ProfileActivity", "Edad: " + emailEditText.getText());
    }



    public void buttonSave(View view){
        Intent intent = new Intent(this, ProfileActivity.class );
        startActivity(intent);
    }

    public void buttonDelete(View view){
        Intent intent = new Intent(this, ProfileActivity.class );
        startActivity(intent);
    }


    @Override
    protected void onStart () {
        super.onStart();
        Log.d("MainActivity", "ANTONIO: onStart");
    }

    @Override
    protected void onResume () {
        super.onResume();
        Log.d("MainActivity", "ANTONIO: onResume");

    }

    @Override
    protected void onPause () {
        super.onPause();
        Log.d("MainActivity", "ANTONIO: onPause");
    }

    @Override
    protected void onStop () {
        super.onStop();
        Log.d("MainActivity", "ANTONIO: onStop");
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        Log.d("MainActivity", "ANTONIO: onDestroy");
    }

    @Override
    protected void onRestart () {
        super.onRestart();
        Log.d("MainActivity", "ANTONIO: onRestart");
    }





}
