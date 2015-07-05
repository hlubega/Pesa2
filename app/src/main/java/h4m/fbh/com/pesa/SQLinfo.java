package h4m.fbh.com.pesa;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.sql.SQLException;


public class SQLinfo extends Activity{

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlinfo);
        tv = (TextView) findViewById(R.id.tvSQLinfo);

        DatabaseHandler info = new DatabaseHandler(this);
        try {
            info.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String data = info.getDat();
        info.close();
        tv.setText(data);

    }


}
