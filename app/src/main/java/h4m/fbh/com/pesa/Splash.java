package h4m.fbh.com.pesa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(4000);

                }catch(InterruptedException e){
                    e.printStackTrace();

                }finally {
                    Intent intent = new Intent(Splash.this,Overview.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }


}
