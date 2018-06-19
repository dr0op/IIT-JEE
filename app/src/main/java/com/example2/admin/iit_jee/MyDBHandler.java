package com.example2.admin.iit_jee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Admin on 11-05-2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "rankStore.db";
    public static final String TABLE_RANK = "file1";
    public static final String TABLE_COLLEGE="file2";
    public static final String TABLE_BRANCH="file3";
    public static final String QUOTA = "QUOTA";
    public static final String COLLEGE_CODE = "College_Code";
    public static final String BRANCH_CODE = "Branch_Code";
    public static final String OPO = "opo";
    public static final String OPC = "opc";
    public static final String SCO = "sco";
    public static final String SCC = "scc";
    public static final String STO = "sto";
    public static final String STC = "stc";
    public static final String BCO = "bco";
    public static final String BCC = "bcc";
    public static final String C_ID ="c_id";
    public static final String C_NAME="c_name";
    public static final String B_NAME="b_name";
    public static final String B_ID="b_id";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_RANK + "(" +
                QUOTA + " VARCHAR(2) NOT NULL, " +
                COLLEGE_CODE + " VARCHAR(3) NOT NULL, " +
                BRANCH_CODE + " Varchar(4) NOT NULL," +
                OPO + " INT ," +
                OPC + " INT, " +
                SCO + " INT, " +
                SCC + " INT," +
                STO + " INT, " +
                STC + " INT ," +
                BCO + " INT, " +
                BCC + " INT  " +
                ");";
        String query2="CREATE TABLE " + TABLE_COLLEGE + "(" +
                C_ID+ " VARCHAR(3) NOT NULL  , " +
                C_NAME + " TEXT NOT NULL " +
                ");";
        String query3="CREATE TABLE " + TABLE_BRANCH + "(" +
                B_ID+ "  varchar(4) NOT NULL ," +
                B_NAME + " TEXT NOT NULL " +
                ");";

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RANK);
        db.execSQL("DROP TABLE IF EXITS " + TABLE_COLLEGE);
        db.execSQL("DROP TABLE IF EXITS " + TABLE_BRANCH);
        onCreate(db);
    }
    //Add a new row to the database
    public  void addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("QUOTA",product.get_QUOTA());
        values.put("College_Code",product.get_college());
        values.put("Branch_Code",product.get_branch());
        values.put("opo",product.get_OPO());
        values.put("opc",product.get_OPC());
        values.put("sco",product.get_SCO());
        values.put("scc",product.get_SCC());
        values.put("sto",product.get_STO());
        values.put("stc",product.get_STC());
        values.put("bco",product.get_BCO());
        values.put("bcc",product.get_BCC());
        db.insert(TABLE_RANK, null, values);
        db.close();
        return ;
    }
    public  void addProduct1(product1 product1){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_ID,product1.get_cid());
        values.put(C_NAME,product1.get_cname());
        db.insert(TABLE_COLLEGE, null, values);
        db.close();
        return ;
    }
    public  void addProduct2(product2 product){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(B_ID,product.get_bid());
        values.put(B_NAME,product.get_bname());
        db.insert(TABLE_BRANCH, null, values);
        db.close();
        return ;
    }

    public String databaseToString(int q,int rank){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
       String query = "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
              " file3 on file3.b_id==file1.Branch_Code;";
        if(rank<1000){
            rank-=100;
        }
        else if(rank>1000 &&rank<5000){
            rank-=250;
        }
        else if(rank>5000 && rank<20000){
            rank-=500;
        }
        else if(rank>20000 && rank<50000){
            rank-=750;
        }
        else{
            rank-=2000;
        }

        if(q==1){
           query= "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                    " file3 on file3.b_id==file1.Branch_Code where file1.opc> "+rank+" and file2.c_id like "+"'1%'"+"order by file1.opo asc" ;
        }
        else if(q==2){
            query = "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                    " file3 on file3.b_id==file1.Branch_Code where file1.scc> "+rank+ " and file2.c_id like "+"'1%'"+" order by file1.sco;";
        }
        else if(q==3){
            query = "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                    " file3 on file3.b_id==file1.Branch_Code where file1.stc> "+rank+ " and file2.c_id like "+"'1%'"+" order by file1.sto;";
        }
        else if(q==4){
           query = "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                    " file3 on file3.b_id==file1.Branch_Code where file1.bcc> "+rank+" and file2.c_id like "+"'1%'"+" order by file1.bco ;";
        }





        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        int l=c.getCount();
        dbString+="Total available choices ="+l+"#";
        int i=1;
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            dbString+=i+".";
            i++;
            dbString+="Quota:- ";
            dbString+=c.getString(c.getColumnIndex("a"));
            dbString+="\n";
            dbString+="Branch:- ";
            dbString += c.getString(c.getColumnIndex("b"));
            dbString+="\n";
            dbString+="College:- ";
            dbString += c.getString(c.getColumnIndex("c"));
            dbString += "#";


            c.moveToNext();
        }
        db.close();
        return dbString;
    }
    public String databaseToString(int q,int rank,int we){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                " file3 on file3.b_id==file1.Branch_Code;";
        if(rank<1000){
            rank-=100;
        }
        else if(rank>1000 &&rank<5000){
            rank-=250;
        }
        else if(rank>5000 && rank<20000){
            rank-=500;
        }
        else if(rank>20000 && rank<50000){
            rank-=750;
        }
        else{
            rank-=2000;
        }

        if(q==1){
            query= "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                    " file3 on file3.b_id==file1.Branch_Code where file1.opc> "+rank+" and file2.c_id not like "+"'1%'"+"order by file1.opo asc" ;
        }
        else if(q==2){
            query = "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                    " file3 on file3.b_id==file1.Branch_Code where file1.scc> "+rank+ " and file2.c_id not like "+"'1%'"+" order by file1.sco;";
        }
        else if(q==3){
            query = "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                    " file3 on file3.b_id==file1.Branch_Code where file1.stc> "+rank+ " and file2.c_id not like "+"'1%'"+" order by file1.sto;";
        }
        else if(q==4){
            query = "SELECT file1.QUOTA as a,file3.b_name as b,file2.c_name as c from file2 left outer join file1 on file1.College_Code==file2.c_id left outer join "+
                    " file3 on file3.b_id==file1.Branch_Code where file1.bcc> "+rank+" and file2.c_id not like "+"'1%'"+" order by file1.bco ;";
        }





        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        int l=c.getCount();
        dbString+="Total available choices ="+l+"#";
        int i=1;
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            dbString+=i+".";
            i++;
            dbString+="Quota:- ";
            dbString+=c.getString(c.getColumnIndex("a"));
            dbString+="\n";
            dbString+="Branch:- ";
            dbString += c.getString(c.getColumnIndex("b"));
            dbString+="\n";
            dbString+="College:- ";
            dbString += c.getString(c.getColumnIndex("c"));
            dbString += "#";

            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}