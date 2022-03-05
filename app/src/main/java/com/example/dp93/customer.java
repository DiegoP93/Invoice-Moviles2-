package com.example.dp93;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class customer extends AppCompatActivity {
    EditText idcust,name,email,phone;
    Button btnsave,btnsearch,btninvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        idcust= findViewById(R.id.etidcust);
        name= findViewById(R.id.etname);
        email= findViewById(R.id.etemail);
        phone= findViewById(R.id.etphone);

        btnsave= findViewById(R.id.btnsave);
        btnsearch= findViewById(R.id.btnsearch);
        btninvoice= findViewById(R.id.btninvoice);

        btninvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Invoice sohInvoice = new Invoice(getApplicationContext(),"dbinvoice",null,1);
                SQLiteDatabase db = sohInvoice.getWritableDatabase();
                ContentValues cust = new ContentValues();
                try {
                    cust.put("idcust",idcust.getText().toString());
                    cust.put("name",name.getText().toString());
                    cust.put("email",email.getText().toString());
                    cust.put("phone",phone.getText().toString());

                    db.insert("customer",null,cust);
                    db.close();
                    Toast.makeText(getApplicationContext(),"cliente agregado correctamente",Toast.LENGTH_SHORT).show();

                    idcust.setText("");
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Invoice sohInvoice = new Invoice(getApplicationContext(),"dbinvoice",null,1);
                SQLiteDatabase db = sohInvoice.getReadableDatabase();

                String sql = "Select name,email,phone From customer Where idcust = '"+idcust.getText().toString()+"'";
                Cursor ccustomer = db.rawQuery(sql,null);
                if (ccustomer.moveToFirst())
                {
                    name.setText(ccustomer.getString(0));
                    email.setText(ccustomer.getString(1));
                    phone.setText(ccustomer.getString(2));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"cliente no existe!",Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
    }
}