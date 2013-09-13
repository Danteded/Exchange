package com.example.HHSender;

import java.util.Calendar;

import com.example.HHSender.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText edName;
	private EditText edPosition;
	private EditText edSalary;
	private EditText edPhone;
	private EditText edEmail;
	private TextView tvDate;
	public Button btSave;
	private String showAnswer;
	private Spinner spinner;
	Context context = MainActivity.this;
	
	final Calendar c = Calendar.getInstance();
	int DIALOG_DATE = 1;
	int myYear = c.get(Calendar.YEAR);
	int myMonth = c.get(Calendar.MONTH);
	int myDay = c.get(Calendar.DAY_OF_MONTH);
	
	public static final int getAnswer = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		edName=(EditText) findViewById(R.id.edName);
		edPosition=(EditText) findViewById(R.id.edPosition);
		edSalary=(EditText) findViewById(R.id.edSalary);
		edPhone=(EditText) findViewById(R.id.edPhone);
		edEmail=(EditText) findViewById(R.id.edEmail);
		
		btSave=(Button) findViewById(R.id.btSend);

		tvDate = (TextView)	findViewById(R.id.tvDate);
		
		spinner = (Spinner) findViewById(R.id.gender_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.gender, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		tvDate.setOnClickListener(new TextView.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_DATE);			
			}
		});
		
		
		
		btSave.setOnClickListener( new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int flag = 0;
				switch(0) {
					case 0: if (edName.getText().length() == 0) {
							flag = 1;
							break;
					}
					case 1: if (tvDate.getText().toString().equals(getResources().getString(R.string.chooseDate))) {
							flag = 1;
							break;
					}		
					case 2: if (edPosition.getText().length() == 0) {
							flag = 1;
							break;
					}				
					case 3: if (edSalary.getText().length() == 0) {
							flag = 1;
							break;
					}
					case 4: if (edPhone.getText().length() == 0) {
							flag = 1;
							break;
					}
					case 5: if (edEmail.getText().length() == 0) {
							flag = 1;
							break;
					}
				};

				if( flag ==1) {
					AlertDialog alertDialog = new AlertDialog.Builder(context).create();
					alertDialog.setTitle(getResources().getString(R.string.Attention));
					alertDialog.setMessage(getResources().getString(R.string.NotEnoughData));
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
				        	dialog.cancel();
				        }
				    });
					alertDialog.show();
				}
				else {
				    Intent Sendintent = new Intent(MainActivity.this, second_activity.class);
				    Sendintent.putExtra("name",edName.getText().toString());
				    Sendintent.putExtra("date",tvDate.getText().toString());
				    Sendintent.putExtra("gender",spinner.getSelectedItem().toString());
				    Sendintent.putExtra("position",edPosition.getText().toString());
				    Sendintent.putExtra("salary",edSalary.getText().toString());
				    Sendintent.putExtra("phone",edPhone.getText().toString());
				    Sendintent.putExtra("email",edEmail.getText().toString());
				    startActivityForResult(Sendintent, getAnswer);
				}
			}
		});
	}

    protected Dialog onCreateDialog(int id) {
    	if (id == DIALOG_DATE) {
    		DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
    		return tpd;
    	}
    	return null;
    }
      
    OnDateSetListener myCallBack = new OnDateSetListener() {

    	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    		myYear = year;
    		myMonth = monthOfYear + 1;
    		myDay = dayOfMonth;
    		tvDate.setText(myDay + "." + myMonth + "." + myYear);
    	}
    };
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);

    	if (requestCode == getAnswer) {
    		if (resultCode == RESULT_OK) {
    			showAnswer = data.getStringExtra(second_activity.answer);
			    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
				alertDialog.setTitle(getResources().getString(R.string.AnswerTitle));
				alertDialog.setMessage(showAnswer);
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	dialog.cancel();
			        }
			    });
				alertDialog.show();
    		} 
    	} 
    }
}

