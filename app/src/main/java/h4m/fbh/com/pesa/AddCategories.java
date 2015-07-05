package h4m.fbh.com.pesa;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by fbh on 12/04/2015.
 */
public class AddCategories extends ActionBarActivity implements View.OnClickListener{

    EditText edCatName;
    Spinner addCatSpinner;
    Button save;
    Button cancel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_categories);

        edCatName = (EditText) findViewById(R.id.edAddCategoryName);
        save = (Button) findViewById(R.id.btnAddCatSave);
        //cancel = (Button) findViewById(R.id.btnAddCatCancel);
        addCatSpinner = (Spinner) findViewById(R.id.spinnerTransactionType);
        save.setOnClickListener(this);
        //cancel.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this, R.array.addCat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        addCatSpinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()){
            case R.id.btnAddCatSave:
                boolean check = true;
                try{
                String catName = edCatName.getText().toString();
                String catSpinner = addCatSpinner.getSelectedItem().toString();

                DatabaseHandler entry = new DatabaseHandler(AddCategories.this);
                entry.open();
                entry.insertNewCategory(catName, catSpinner);
                entry.close();
                }catch (Exception e){
                    check = false;

                }
                finally {
                    Context tst = getApplicationContext();
                    CharSequence text = "Item Added";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(tst, text, duration);
                    toast.show();
                    edCatName.setText("");

                }
                break;


        }

    }
}
