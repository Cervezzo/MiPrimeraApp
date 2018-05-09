package miprimeraapp.android.teaching.com.miprimeraapp;

        import android.content.Intent;
        import android.drm.DrmStore;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;

public class MainActivity extends AppCompatActivity {



    public void onClick(View view){

        Intent intent = new Intent(this, SecondActivity.class);

    }

    public void onClick1(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://giphy.com/explore/biblioteca"));

    startActivity(intent);}

    public void onClick2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("tel:1234"));
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "ANTONIO: onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "ANTONIO: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "ANTONIO: onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "ANTONIO: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "ANTONIO: onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MainActivity","ANTONIO: onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MainActivity","ANTONIO: onRestart");
    }
}
