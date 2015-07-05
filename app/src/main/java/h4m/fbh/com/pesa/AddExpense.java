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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by fbh on 03/03/2015.
 */
public class AddExpense extends ActionBarActivity implements View.OnClickListener{


    EditText payee;
    EditText amount;
    EditText expDate;
    Spinner cat_spinner;
    Button btnsave;
    Spinner status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
        expDate = (EditText) findViewById(R.id.edDateExp);
        payee = (EditText) findViewById(R.id.edpayeeExp);
        amount = (EditText) findViewById(R.id.edAmountExp);
        btnsave = (Button) findViewById(R.id.btnSaveExp);
        cat_spinner = (Spinner) findViewById(R.id.spinner_Category);
        status = (Spinner) findViewById(R.id.spinner_clearedExp);

        btnsave.setOnClickListener(this);
        expDate.setOnClickListener(this);

      //  Spinner stat_spinner = (Spinner) findViewById(R.id.spinner_clearedExp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        status.setAdapter(adapter);

        //Spinner cat_spinner = (Spinner) findViewById(R.id.spinner_Category);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.expenseCategory_spinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        cat_spinner.setAdapter(adapter1);

}
Calendar myCalender = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalender.set(Calendar.YEAR,year);
            myCalender.set(Calendar.MONTH, monthOfYear);
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    public void onClick(View arg0) {
        switch(arg0.getId()){
            case R.id.btnSaveExp:

                boolean hasItWorked = true;

                try{
                String paye = payee.getText().toString();
                int amo = Integer.parseInt(amount.getText().toString());
                String cat = cat_spinner.getSelectedItem().toString();
                String statSpinner = status.getSelectedItem().toString();

                    String dat = expDate.getText().toString();
                //Editable amo = amount.getText();

                DatabaseHandler entry = new DatabaseHandler(AddExpense.this);
                entry.open();
                entry.insertExp(paye, amo, cat, statSpinner, dat);
                entry.close();
                }catch (Exception e){
                    hasItWorked = false;
                }finally {
                    if (hasItWorked){

                        Context tst = getApplicationContext();
                        CharSequence text = "Transaction Saved";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(tst, text, duration);
                        toast.show();

                        payee.setText("");
                        amount.setText("");
                        payee.setText("");
                        expDate.setText("");
                    }
                }


                break;

            case R.id.edDateExp:
                new DatePickerDialog(AddExpense.this, date, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
                break;
            /*case R.id.btnCancelExp:
                Intent goback;
                goback = new Intent(this, Overview.class);
                startActivity(goback);*/

    }}

    private void updateLabel() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        expDate.setText(sdf.format(myCalender.getTime()));
    }
}
