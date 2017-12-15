package com.singpaulee.tiketbioskop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.singpaulee.tiketbioskop.DatabaseHelper.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

	private EditText edtLoginNickname;
	private EditText edtLoginPassword;
	private LinearLayout line;
	private TextView tvForgotPassword;
	private Button btnLogin;
	private Button btnRegister;

	String firstname, lastname, nickname, email, password, telp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(verifikasi(edtLoginNickname.getText().toString().trim(), edtLoginPassword.getText().toString().trim())){
					startActivity(new Intent(LoginActivity.this,MainActivity.class));
				}else {
					Toast.makeText(LoginActivity.this, "Wrong Nickname/Password", Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			}
		});

		tvForgotPassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
			}
		});
	}

	private boolean verifikasi(String nickname, String password) {
		DatabaseHelper database = new DatabaseHelper(this);
		SQLiteDatabase db = database.getReadableDatabase();

		String[] column = {"firstname","lastname","email","telp"};
		String selection = "nickname = ? AND password = ?";
		String[] selectionArgs = {nickname,password};
		Cursor cursor = db.query("user",column,selection,selectionArgs,null,null,null);
		int cursorCount =cursor.getCount();
		if(cursorCount>0){
//			firstname = cursor.getString(0);
//			lastname = cursor.getString(1);
//			email = cursor.getString(2);
//			telp = cursor.getString(3);
			return true;
		}
		return false;
	}

	private void initView() {
		edtLoginNickname = findViewById(R.id.edt_login_nickname);
		edtLoginPassword = findViewById(R.id.edt_login_password);
		line = findViewById(R.id.line);
		tvForgotPassword = findViewById(R.id.tv_forgot_password);
		btnLogin = findViewById(R.id.btn_login);
		btnRegister = findViewById(R.id.btn_register);
	}
}
