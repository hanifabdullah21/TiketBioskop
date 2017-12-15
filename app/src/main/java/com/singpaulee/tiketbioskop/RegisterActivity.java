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

public class RegisterActivity extends AppCompatActivity {

	private EditText edtFirstname;
	private EditText edtLastname;
	private EditText edtNickname;
	private EditText edtEmail;
	private EditText edtPassword;
	private EditText edtVerpassword;
	private EditText edtMobilenumber;
	private Button btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
		btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				register();
			}
		});
	}

	private void register() {
		if(!edtVerpassword.getText().toString().equals(edtPassword.getText().toString())){
			edtVerpassword.setError("Wrong Verivication Password");
			return;
		}

		String firstname,lastname,nickname, email, password,telp;
		firstname = edtFirstname.getText().toString().trim();
		lastname = edtLastname.getText().toString().trim();
		nickname = edtNickname.getText().toString().trim();
		email = edtEmail.getText().toString().trim();
		password = edtPassword.getText().toString().trim();
		telp = edtMobilenumber.getText().toString().trim();

		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		try {
			String query = "INSERT INTO user(firstname,lastname,nickname,email,password,telp) VALUES ('"+firstname+"','"+lastname+"','"+nickname+"','"+email+"','"+password+"','"+telp+"')";
			db.execSQL(query);
		}catch (SQLException sql){
			Toast.makeText(this, ""+sql, Toast.LENGTH_SHORT).show();
			Log.d("SqlExceptionRegister", "register: SQL EXCEPTION "+sql);
		}
		Toast.makeText(this, "Register Succes", Toast.LENGTH_SHORT).show();

	}

	private void initView() {
		edtFirstname = findViewById(R.id.edt_firstname);
		edtLastname = findViewById(R.id.edt_lastname);
		edtNickname = findViewById(R.id.edt_nickname);
		edtEmail = findViewById(R.id.edt_email);
		edtPassword = findViewById(R.id.edt_password);
		edtVerpassword = findViewById(R.id.edt_verpassword);
		edtMobilenumber = findViewById(R.id.edt_mobilenumber);
		btnRegister = findViewById(R.id.btn_register);
	}
}
