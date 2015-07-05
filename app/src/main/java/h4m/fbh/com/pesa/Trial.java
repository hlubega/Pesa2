package h4m.fbh.com.pesa;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;


public class Trial extends ActionBarActivity {

    Button show;
    TextView showMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        //show = (Button) findViewById(R.id.btnShow);
        showMe = (TextView) findViewById(R.id.tvShowme);

        DatabaseHandler show = new DatabaseHandler(this);
        try {
            show.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String data = show.getDat();
        show.close();
        showMe.setText(data);
    }


}
