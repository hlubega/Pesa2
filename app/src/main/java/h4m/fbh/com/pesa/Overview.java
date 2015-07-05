package h4m.fbh.com.pesa;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by fbh on 01/04/2015.
 */
public class Overview extends ActionBarActivity {

    protected Button addExpense;
    protected Button addIncome;
    public TextView todaysAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        addExpense = (Button) findViewById(R.id.btnAddExpense);
        addIncome = (Button) findViewById(R.id.btnAddIncome);
        todaysAmount = (TextView) findViewById(R.id.tvTodaysamount);

      /*  Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        todaysAmount.setTypeface(tp);*/

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Overview.this, AddExpense.class);
                startActivity(intent);
            }
        });

        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Overview.this, AddIncome.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_addincome: {
                Intent startAddInc = new Intent(Overview.this, AddIncome.class);
                startActivity(startAddInc);

                break;
            }
            case R.id.action_addexpense: {
                Intent startAddExp = new Intent(Overview.this, AddExpense.class);
                startActivity(startAddExp);
                break;
            }
            case R.id.action_budgetset1: {
                Intent k1= new Intent(Overview.this, Budgets1.class);
                startActivity(k1);
                break;
            }
            case R.id.action_categories: {
                Intent startCat = new Intent(Overview.this, AddCategories.class);
                startActivity(startCat);
                break;
            }
            case R.id.action_budgetoverview: {
                Intent startBudgetOverview = new Intent(Overview.this, BudgetOverview.class);
                startActivity(startBudgetOverview);
                break;
            }
            case R.id.action_reports: {
                Intent startAddExp = new Intent(Overview.this, Reports.class);
                startActivity(startAddExp);
                break;
            }
            case R.id.action_about: {
                Intent startAbout = new Intent(Overview.this, About.class);
                startActivity(startAbout);
                break;
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
