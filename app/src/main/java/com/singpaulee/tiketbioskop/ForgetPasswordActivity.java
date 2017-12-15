package com.singpaulee.tiketbioskop;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.singpaulee.tiketbioskop.DatabaseHelper.DatabaseHelper;

import java.util.Random;

public class ForgetPasswordActivity extends AppCompatActivity {

	private EditText edtFpNickname;
	private EditText edtFpEmail;
	private EditText edtFpNumber;
	private Button btnGetKode;
	private LinearLayout lineAfterGetKode;
	private TextView edtTvKode;
	private EditText edtFpNewpass;
	private EditText edtFpVerifnewpass;
	private Button btnChangePass;

	String nickname, email, telp;
	String kode,newpassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);
		initView();

		btnGetKode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				nickname = edtFpNickname.getText().toString().trim();
				email = edtFpEmail.getText().toString().trim();
				telp = edtFpNumber.getText().toString().trim();
				try {
					if (getnewkode(nickname,email,telp)){
						lineAfterGetKode.setVisibility(View.VISIBLE);
						edtTvKode.setText(""+kode);
						edtFpEmail.setText(null);
						edtFpNickname.setText(null);
						edtFpNumber.setText(null);
						Toast.makeText(ForgetPasswordActivity.this, "Oiii", Toast.LENGTH_SHORT).show();
					}
				}catch (SQLException e){
					Log.d("SqlException", "onClick: "+e);
					Toast.makeText(ForgetPasswordActivity.this, ""+e, Toast.LENGTH_SHORT).show();
				}
			}
		});

		btnChangePass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					if(!edtFpVerifnewpass.getText().toString().equals(edtFpNewpass.getText().toString())){
						edtFpVerifnewpass.setError("Wrong!");
					}else {
						newpassword = edtFpNewpass.getText().toString();
					}
					updatePassword(newpassword,email);
				}catch (SQLException e){
					Log.d("ButtonNewPassword", "onClick: "+e);
					Toast.makeText(ForgetPasswordActivity.this, ""+e, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void updatePassword(String newpassword, String email) {
		DatabaseHelper database = new DatabaseHelper(this);
		SQLiteDatabase db = database.getWritableDatabase();
		String q = "UPDATE user SET password = '"+newpassword+"' WHERE email = '"+email+"'";
		db.execSQL(q);
	}

	private boolean getnewkode(String nick,String mail, String number) {
		DatabaseHelper database = new DatabaseHelper(this);
		SQLiteDatabase db = database.getReadableDatabase();

		//yang akan diambil
		String[] column = {"id","password"};
		//kolom parameter
		String selection = "nickname = ? AND email = ? AND telp = ?";
		//value parameter
		String[] selectionArgs = {nick,mail,number};

		Cursor cursor = db.query("user",column,selection,selectionArgs,null,null,null);
		int cursorCount =cursor.getCount();
		if(cursorCount>0){
//			int id = cursor.getInt(0);
			String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
			for (int a = 1; a <= 5; a++) {
				kode += CharSet.charAt(new Random().nextInt(CharSet.length()));
			}
			return true;
		}
		return false;
	}

	private void initView() {
		edtFpNickname = findViewById(R.id.edt_fp_nickname);
		edtFpEmail = findViewById(R.id.edt_fp_email);
		edtFpNumber = findViewById(R.id.edt_fp_number);
		btnGetKode = findViewById(R.id.btn_get_kode);
		lineAfterGetKode = findViewById(R.id.line_after_get_kode);
		edtTvKode = findViewById(R.id.edt_tv_kode);
		edtFpNewpass = findViewById(R.id.edt_fp_newpass);
		edtFpVerifnewpass = findViewById(R.id.edt_fp_verifnewpass);
		btnChangePass = findViewById(R.id.btn_change_pass);
	}
}
