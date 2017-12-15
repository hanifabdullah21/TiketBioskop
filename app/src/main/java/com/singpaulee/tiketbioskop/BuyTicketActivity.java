package com.singpaulee.tiketbioskop;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.singpaulee.tiketbioskop.DatabaseHelper.DatabaseHelper;

public class BuyTicketActivity extends AppCompatActivity {

	private EditText mEdtName;
	private EditText mEdtPhone;
	private EditText mEdtQuantity;
	private Button mBtnBuy;
	String phone, nama, title_movie;
	int q;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_ticket);
		initView();

		mBtnBuy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				title_movie = getIntent().getStringExtra("title_movie");
				nama = mEdtName.getText().toString().trim();
				phone = mEdtPhone.getText().toString().trim();
				q = Integer.valueOf(mEdtQuantity.getText().toString().trim());
				buyticket();
			}
		});
	}

	private void buyticket() {
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		try {
			String query = "INSERT INTO customer(title_movie,name,quantity,phone) VALUES ('"+title_movie+"','"+nama+"','"+q+"','"+phone+"')";
			db.execSQL(query);
		}catch (SQLException sql){
			Toast.makeText(this, ""+sql, Toast.LENGTH_SHORT).show();
			Log.d("SqlExceptionRegister", "register: SQL EXCEPTION "+sql);
		}
		Toast.makeText(this, "Register Succes", Toast.LENGTH_SHORT).show();
		finish();
	}

	private void initView() {
		mEdtName = findViewById(R.id.edt_name);
		mEdtPhone = findViewById(R.id.edt_phone);
		mEdtQuantity = findViewById(R.id.edt_quantity);
		mBtnBuy = findViewById(R.id.btn_buy);
	}
}
