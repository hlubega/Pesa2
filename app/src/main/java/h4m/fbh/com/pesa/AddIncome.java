package h4m.fbh.com.pesa;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fbh on 26/03/2015.
 */
public class AddIncome extends ActionBarActivity implements View.OnClickListener{

    EditText edDate;
    EditText incomeAmount;
    Button save;
   // Button cancel;
    Spinner payer_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        incomeAmount = (EditText) findViewById(R.id.edIncAmount);
        save = (Button) findViewById(R.id.btnSaveInc);
       // cancel = (Button) findViewById(R.id.btnCancelInc);
        edDate = (EditText) findViewById(R.id.edDateInc);
        edDate.setOnClickListener(this);
        save.setOnClickListener(this);
       // cancel.setOnClickListener(this);

        payer_spinner = (Spinner) findViewById(R.id.spinner_inc);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.income_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        payer_spinner.setAdapter(adapter);
    }
    Calendar myCalender = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalender.set(Calendar.YEAR, year);
            myCalender.set(Calendar.MONTH, monthOfYear);
            myCalender.set(Calendar.DAY_OF_WEEK, dayOfMonth);
            myCalender.getTimeInMillis();
            //Date d = new Date(myCalender.getTimeInMillis());
            updateField();
        }

        private void updateField() {

            String myFormat = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
            edDate.setText(sdf.format(myCalender.getTime()));
        }
    };

    @Override
    public void onClick(View arg0) {

        switch(arg0.getId()){
            case R.id.btnSaveInc:

                boolean hasItWorked = true;
                try{
                String paySpinner = payer_spinner.getSelectedItem().toString();
                int inc_amo = Integer.parseInt(incomeAmount.getText().toString());
               // String
                       int inc_dat = Integer.parseInt(edDate.getText().toString());

                    DatabaseHandler entry = new DatabaseHandler(AddIncome.this);
                    entry.open();
                    entry.insertInc(paySpinner, inc_amo, inc_dat);
                    entry.close();
                }catch (Exception e){
                    hasItWorked = false;
                    String error = e.toString();
                    Dialog d =new Dialog(this);
                    d.setTitle("Ooops!!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();

                }
                finally {
                    if (hasItWorked){

                        Context tst = getApplicationContext();
                        CharSequence text = "Transaction Saved";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(tst, text, duration);
                        toast.show();

                        incomeAmount.setText("");
                        edDate.setText("");

                    }
                }


                break;


            case R.id.edDateInc:
                new DatePickerDialog(AddIncome.this, date, myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }

    }


}
