package h4m.fbh.com.pesa;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Budgets1 extends ActionBarActivity implements View.OnClickListener{

    static  final int DATE_DIALOG_ID = 0;
    private int mYear, mMonth, mDay;
    Spinner budgetCatSpinner;
    Button save;
    Button cancel;
    EditText dateFrom;
    EditText dateTo;
    EditText amo;
    final Calendar cal = Calendar.getInstance();
    final Calendar cal1 = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgets1);

        budgetCatSpinner = (Spinner) findViewById(R.id.spinnerBudgetCat);
        dateFrom = (EditText) findViewById(R.id.edDateBudgetFrom);
        dateTo = (EditText) findViewById(R.id.edDateTo);
        save = (Button) findViewById(R.id.btnBudgetSave);
        amo = (EditText) findViewById(R.id.edBudgetAmount);


        save.setOnClickListener(this);
        dateFrom.setOnClickListener(this);
        dateTo.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expenseCategoryBudgets_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        budgetCatSpinner.setAdapter(adapter);

    }

    DatePickerDialog.OnDateSetListener dates = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updatelabel();

        }
    };

    DatePickerDialog.OnDateSetListener dateto = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cal1.set(Calendar.YEAR, year);
            cal1.set(Calendar.MONTH, monthOfYear);
            cal1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updatelabel1();
        }
    };




    @Override
    public void onClick(View arg0) {

        switch(arg0.getId()){
            case R.id.edDateBudgetFrom:
                new DatePickerDialog(Budgets1.this, dates, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)
                ,cal.get(Calendar.DAY_OF_MONTH)).show();

                     break;
            case R.id.edDateTo:
                new DatePickerDialog(Budgets1.this, dateto, cal1.get(Calendar.YEAR), cal.get(Calendar.MONTH)
                        ,cal.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btnBudgetSave:

                boolean hasItWorked = true;
                String catSpinner = budgetCatSpinner.getSelectedItem().toString();
                String Bdate = dateFrom.getText().toString();
                String BdateTo = dateTo.getText().toString();
                int Bamo = Integer.parseInt(amo.getText().toString());

                DatabaseHandler entry = new DatabaseHandler(Budgets1.this);
                try {
                    entry.open();

                    if (Bdate.isEmpty() && BdateTo.isEmpty()){
                        Context t = getApplication();
                        CharSequence text = "Please fill all Fields";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(t,text,duration).show();
                    }else{
                    entry.insertBudgetSet(catSpinner, Bdate, BdateTo, Bamo);
                    }
                    entry.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    if (hasItWorked){
                        Context ts = getApplication();
                        CharSequence text = "Budget Details Saved";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(ts,text,duration).show();
                        //Toast toast = Toast.makeText(ts, text, duration);
                       // toast.show();
                        dateFrom.setText("");
                        dateTo.setText("");
                        amo.setText("");
                    }

                }

                break;
            /*case R.id.btnBudgetCancel:
                Intent goback = new Intent(Budgets1.this, Overview.class);
                startActivity(goback);

                break;*/
        }


    }
    private void updatelabel() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        dateFrom.setText(sdf.format(cal.getTime()));


    }
    private void updatelabel1() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        dateTo.setText(sdf1.format(cal1.getTime()));
    }

}
