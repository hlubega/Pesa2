package h4m.fbh.com.pesa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fbh on 17/06/2015.
 */
public class DatabaseHandler {

    //Database Name and version
    private static final String DATABASE_NAME = "Pesa_DB";
    private static final int DATABASE_VERSION = 1;

    //Add expenses table
    private static final String DATABASE_TABLE1 = "Expenses";
    private static final String KEY_ROWID1="_id";
    private static final String KEY_PAYEE1="payee";
    private static final String KEY_AMOUNT1="amount1";
    private static final String KEY_CATEGORY1="categpry";
    private static final String KEY_STATUS1="status";
    private static final String KEY_DATE1="date";
    //Add income table
    private static final String DATABASE_TABLE2 = "income";
    private static final String KEY_ROWID2="_id";
    private static final String KEY_PAYER="payer";
    private static final String KEY_AMOUNT2="amount2";
    private static final String KEY_DATE2="date";
    //expenses category table
    private static final String DATABASE_TABLE3="expensesCategory";
    private static final String KEY_ROWID3="_id";
    private static final String KEY_CATEGORY2="categoryName";
    //Income category table
    private static final String DATABASE_TABLE4="incomeCategory";
    private static final String KEY_ROWID4="_id";
    private static final String KEY_CATEGORY3="categoryName";

    //Budgets table
    private static final String DATABASE_TABLE5="budget";
    private static final String KEY_ROWID5="_id";
    private static final String KEY_CATEGORY4="categroy";
    private static final String KEY_AMOUNT4="amount3";
    private static final String KEY_FROMDATE="fromDate";
    private static final String KEY_TODATE="toDate";

    //Table create statements
    //Add expenses table
    private static final String CREATE_EXPENSES_TABLE = "CREATE TABLE " + DATABASE_TABLE1 +
            "(" + KEY_ROWID1 + " INTEGER PRIMARY KEY, " + KEY_PAYEE1 + " TEXT, " + KEY_AMOUNT1 + " INTEGER, " +
            KEY_CATEGORY1 + " TEXT, " + KEY_STATUS1 + " TEXT, " + KEY_DATE1 + " INTEGER" + ")";

    //Add Income table
    private static final String CREATE_INCOME_TABLE = "CREATE TABLE " + DATABASE_TABLE2 +
            "(" + KEY_ROWID2 + " INTEGER PRIMARY KEY, " + KEY_PAYER + " TEXT, " + KEY_AMOUNT2 + " INTEGER, " + KEY_DATE2 + " INTEGER" + ")";

    //Expense category table
    private static final String CREATE_EXPCATEGORY_TABLE = "CREATE TABLE " + DATABASE_TABLE3 +
            "(" + KEY_ROWID3 + " INTEGER PRIMARY KEY, " + KEY_CATEGORY2 + " TEXT" + ")";

    //Income category table
    private static final String CREATE_INCCATEGORY_TABLE = "CREATE TABLE " + DATABASE_TABLE4 +
            "(" + KEY_ROWID4 + " INTEGER PRIMARY KEY, " + KEY_CATEGORY3 + " TEXT" + ")";

    //Budgets table
    private static final String CREATE_BUDGET_TABLE = "CREATE TABLE " + DATABASE_TABLE5 +
            "(" + KEY_ROWID5 + " INTEGER PRIMARY KEY, " + KEY_CATEGORY4 + " TEXT, "  + KEY_FROMDATE + " INTEGER, " + KEY_TODATE + " INTEGER"  +
            KEY_AMOUNT4 + " INTEGER" + ")";

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;




    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Creating table
            db.execSQL(CREATE_EXPENSES_TABLE);
            db.execSQL(CREATE_INCOME_TABLE);
            db.execSQL(CREATE_EXPCATEGORY_TABLE);
            db.execSQL(CREATE_INCCATEGORY_TABLE);
            db.execSQL(CREATE_BUDGET_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //on upgrade drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE2);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE3);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE4);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE5);

            onCreate(db);

        }
    }

    public DatabaseHandler(Context c){
        ourContext = c;
    }
    public DatabaseHandler open()throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }
    //method to add records to database from expenses activity
    public long insertExp(String paye, int amo, String cat, String statSpinner, String dat) {
        ContentValues insertExpCV = new ContentValues();
        insertExpCV.put(KEY_PAYEE1, paye);
        insertExpCV.put(KEY_AMOUNT1, amo);
        insertExpCV.put(KEY_CATEGORY1, cat);
        insertExpCV.put(KEY_STATUS1, statSpinner);
        insertExpCV.put(KEY_DATE1, dat);
        return ourDatabase.insert(DATABASE_TABLE1, null, insertExpCV);
    }

    public long insertInc(String paySpinner, int inc_amo, int inc_dat) {
        ContentValues insertIncCV = new ContentValues();
        insertIncCV.put(KEY_PAYER, paySpinner);
        insertIncCV.put(KEY_AMOUNT2, inc_amo);
        insertIncCV.put(KEY_DATE2, inc_dat);
        return ourDatabase.insert(DATABASE_TABLE2, null, insertIncCV);
    }

    public long insertNewCategory(String catName, String catSpinner) {
        ContentValues insertNewCatCV = new ContentValues();

        if (catSpinner == "Income"){
            insertNewCatCV.put(KEY_CATEGORY3, catName);
            return ourDatabase.insert(DATABASE_TABLE4, null, insertNewCatCV);

        }else{
            insertNewCatCV.put(KEY_CATEGORY2, catName);
            return ourDatabase.insert(DATABASE_TABLE3, null, insertNewCatCV);
        }
    }

    public long insertBudgetSet(String catSpinner, String bdate, String bdateTo, int bamo) {
        ContentValues insertBudgetSetCV = new ContentValues();
        insertBudgetSetCV.put(KEY_CATEGORY4, catSpinner);
        insertBudgetSetCV.put(KEY_FROMDATE, bdate);
        insertBudgetSetCV.put(KEY_TODATE, bdateTo);
        insertBudgetSetCV.put(KEY_AMOUNT4, bamo);

        return ourDatabase.insert(DATABASE_TABLE5, null, insertBudgetSetCV);
    }

    public String getData() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String currentDate = sdf.format(cal.getTime());

        String result = null;
        if (currentDate == KEY_DATE1) {
            String[] cols = new String[]{KEY_ROWID1, KEY_PAYEE1, KEY_AMOUNT1,KEY_CATEGORY1,KEY_STATUS1, KEY_DATE1};
            Cursor c = ourDatabase.query(DATABASE_TABLE1, cols, null, null, null, null, null);
            int sum = 0;
            int iRow = c.getColumnIndex(KEY_ROWID1);
            int iAmount = c.getColumnIndex(KEY_AMOUNT1);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                sum = sum + c.getInt(iAmount);
            }
            result = String.valueOf(sum);

        }
        return result;
    }

//calculating total income
   /*public String incomeSum(){

        String[] cols = new String[]{KEY_ROWID2, KEY_PAYER, KEY_AMOUNT2, KEY_DATE2};
        Cursor c = ourDatabase.query(DATABASE_TABLE2, cols, null, null,null, null, null);
        int total = 0;
        int summation = c.getInt(Integer.parseInt(KEY_AMOUNT2));
        for (c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
            total=total+summation;
        }

        return String.valueOf(total);


    }*/


    public String getDat() {
        String[] columns = new String[]{KEY_ROWID1, KEY_PAYEE1, KEY_AMOUNT1,KEY_CATEGORY1,KEY_STATUS1, KEY_DATE1};
        Cursor c =ourDatabase.query(DATABASE_TABLE1, columns, null, null, null, null, null);
        String result = "";
        int iRowv= c.getColumnIndex(KEY_ROWID1);
        int iPayer= c.getColumnIndex(KEY_PAYEE1);
        int iAmpunt= c.getColumnIndex(KEY_AMOUNT1);
        int iCategoryy = c.getColumnIndex(KEY_CATEGORY1);
        int iStatus = c.getColumnIndex(KEY_STATUS1);
        int iDate= c.getColumnIndex(KEY_DATE1);

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){

            Date d = new Date(c.getInt(iDate));
            String s = sdf.format(d);

            result = result +c.getString(iRowv) + " " + c.getString(iPayer) + " " + c.getString(iAmpunt) + " "
                   + c.getString(iCategoryy) + " " + c.getString(iStatus) + " " + c.getString(iDate);
        }

        return result;
    }

}
