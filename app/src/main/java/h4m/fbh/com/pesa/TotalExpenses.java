package h4m.fbh.com.pesa;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by fbh on 28/03/2015.
 */
public class TotalExpenses extends Activity /*implements View.OnClickListener*/{

    EditText edDateFrom, edDateTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_expenses);
        edDateFrom = (EditText) findViewById(R.id.edTotalExpensesDateFrom);
        edDateTo = (EditText) findViewById(R.id.edTotalExpensesTo);

        edDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TotalExpenses.this, date, myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_WEEK)).show();
            }
        });
        edDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TotalExpenses.this, dateto, myCalendarto.get(Calendar.YEAR),myCalendarto.get(Calendar.MONTH),myCalendarto.get(Calendar.DAY_OF_WEEK)).show();

            }
        });


    }

    Calendar myCalender = Calendar.getInstance();


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalender.set(Calendar.YEAR,year);
            myCalender.set(Calendar.MONTH, monthOfYear);
            myCalender.set(Calendar.DAY_OF_WEEK, dayOfMonth);
            updatefromdate();


        }
    };

    Calendar myCalendarto = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener dateto = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendarto.set(Calendar.YEAR,year);
            myCalendarto.set(Calendar.MONTH, monthOfYear);
            myCalendarto.set(Calendar.DAY_OF_WEEK, dayOfMonth);

            updatetodate();

        }
    };



    private void updatefromdate() {

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        edDateFrom.setText(sdf.format(myCalender.getTime()));

    }

    private void updatetodate() {

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        edDateTo.setText(sdf.format(myCalendarto.getTime()));
    }


/*
    @Override
    public void onClick(View v) {

        new DatePickerDialog(TotalExpenses.this, date, myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_WEEK)).show();
        new DatePickerDialog(TotalExpenses.this, dateto, myCalendarto.get(Calendar.YEAR),myCalendarto.get(Calendar.MONTH),myCalendarto.get(Calendar.DAY_OF_WEEK)).show();
    }
    */
}
