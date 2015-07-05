package h4m.fbh.com.pesa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by fbh on 21/06/2015.
 */
public class Reports extends Activity implements View.OnClickListener{

    Button ExpByCat, ExpDaily, ExpWeekly, ExpMonthly;
    Button IncByCat, IncDaily, IncWeekly, IncMonthly;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ExpByCat = (Button)findViewById(R.id.btnExpByCat);
        ExpDaily = (Button)findViewById(R.id.btnExpDaily);
        ExpWeekly = (Button)findViewById(R.id.btnExpWkRep);
        ExpMonthly = (Button)findViewById(R.id.btnExpMnthRep);
        IncByCat = (Button)findViewById(R.id.btnIncCatRep);
        IncDaily = (Button)findViewById(R.id.btnIncDailyRep);
        IncWeekly = (Button)findViewById(R.id.btnIncWkRep);
        IncMonthly = (Button)findViewById(R.id.btnIncMnthRep);

        ExpByCat.setOnClickListener(this);
        ExpDaily.setOnClickListener(this);
        ExpWeekly.setOnClickListener(this);
        ExpMonthly.setOnClickListener(this);
        IncByCat.setOnClickListener(this);
        IncDaily.setOnClickListener(this);
        IncWeekly.setOnClickListener(this);
        IncMonthly.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnExpByCat:
                Intent ExpByCatIntent = new Intent(Reports.this, ExpensesByCat.class);
                startActivity(ExpByCatIntent);
                break;
            case R.id.btnExpDaily:
                Intent ExpDailyIntent = new Intent(Reports.this, ExpDailyReport.class);
                startActivity(ExpDailyIntent);
                break;
            case R.id.btnExpWkRep:
                Intent ExpWkIntent = new Intent(Reports.this, ExpWeeklyReport.class);
                startActivity(ExpWkIntent);
                break;
            case R.id.btnExpMnthRep:
                Intent ExpMonthIntent = new Intent(Reports.this, ExpMonthlyReport.class);
                startActivity(ExpMonthIntent);
                break;
            case R.id.btnIncCatRep:
                Intent IncCatIntent = new Intent(Reports.this, IncomeCatReport.class);
                startActivity(IncCatIntent);
                break;
            case R.id.btnIncDailyRep:
                Intent IncDailyIntent = new Intent(Reports.this, IncDailyReport.class);
                startActivity(IncDailyIntent);
                break;
            case R.id.btnIncWkRep:
                Intent IncWkIntent = new Intent(Reports.this, IncomeWeeklyReport.class);
                startActivity(IncWkIntent);
                break;
            case R.id.btnIncMnthRep:
                Intent IncMonthIntent = new Intent(Reports.this, IncMonthlyReport.class);
                startActivity(IncMonthIntent);
                break;
        }

    }
}
