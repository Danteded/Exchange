package com.example.HHSender;

import com.example.HHSender.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class second_activity extends Activity 
{
	private TextView tv2name;
	private TextView tv2date;
	private TextView tv2gender;
	private TextView tv2position;
	private TextView tv2salary;
	private TextView tv2phone;
	private TextView tv2email;
	private EditText etAnswer;
	private Button bt2back;
	public static final String answer = "12345";
	
    protected void onCreate(Bundle savedInstanceState) 
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);
        tv2name=(TextView) findViewById(R.id.tv2name);
        tv2date=(TextView) findViewById(R.id.tv2date);
        tv2gender=(TextView) findViewById(R.id.tv2gender);
        tv2position=(TextView) findViewById(R.id.tv2position);
        tv2salary=(TextView) findViewById(R.id.tv2salary);
        tv2phone=(TextView) findViewById(R.id.tv2phone);
        tv2email=(TextView) findViewById(R.id.tv2email);
        etAnswer=(EditText) findViewById(R.id.etAnswer);
        bt2back=(Button) findViewById(R.id.bt2back);
        
        tv2name.setText(getResources().getString(R.string.FIO) + getIntent().getStringExtra("name"));
        tv2date.setText(getResources().getString(R.string.BirthDate) + getIntent().getStringExtra("date"));
        tv2gender.setText(getResources().getString(R.string.Gender) + getIntent().getStringExtra("gender"));
        tv2position.setText(getResources().getString(R.string.Position) + getIntent().getStringExtra("position"));
        tv2salary.setText(getResources().getString(R.string.Salary) + getIntent().getStringExtra("salary"));
        

        String PhoneText = getResources().getString(R.string.Phone2, getIntent().getStringExtra("phone"));
        tv2phone.setText(android.text.Html.fromHtml(PhoneText));
        tv2phone.setOnClickListener(new TextView.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+ getIntent().getStringExtra("phone")));
				   startActivity(intent);			
			}
		});
        
        String EmailText=getResources().getString(R.string.Email2, getIntent().getStringExtra("email"));
        tv2email.setText(android.text.Html.fromHtml(EmailText));
        //tv2letter.setText(getIntent().getStringExtra("email"));
        
        tv2email.setOnClickListener(new TextView.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent sendIntent = new Intent();
				sendIntent.setType("message/rfc822");
				sendIntent.setAction(android.content.Intent.ACTION_SEND);
				sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {getIntent().getStringExtra("email")});
				sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.AnswerTitle));
				sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.MailAnswer));
				startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.ChooseClient)));				
			}
		});
        
        bt2back.setOnClickListener( new Button.OnClickListener() {
			public void onClick(View v) {
				Intent answerIntent = new Intent();
				answerIntent.putExtra(answer, etAnswer.getText().toString());
				setResult(RESULT_OK, answerIntent);
				finish();
			}
		});
    }
}