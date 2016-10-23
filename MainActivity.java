package com.example.meto;

import java.util.Calendar;



import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * @author 

 *
 */
public class MainActivity extends Activity {
      private  Button btnTime,btnAdd,btnQuery;
      private EditText editBody,editSubject,editTime;
      private ListView  lvResult;
      MyDatabaseHelper   mydbHelper;
      LinearLayout title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {





		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initAll();
		
		btnAdd.setOnClickListener( new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues values=new ContentValues();
				//创建一个ContentValus对象
				values.put(Memento.Memento1.SUBJECT, editSubject.getText().toString());
				values.put(Memento.Memento1.BOBY, editBody.getText().toString());
				values.put(Memento.Memento1.DATE, editTime.getText().toString());
				Editable contentResolver;
				//values中存值
				contentResolver.insert(Memento.Memento1.MEMENTO_CONTENT_URI,values);
				Toast.makeText(MainActivity.this, "添加生词成功",1000).show();
			}
			
		});
		//事件监听器
		
		btnTime.setOnClickListener(new OnClickListener() {




			
			@Override
			public void onClick(View v) {




				// TODO Auto-generated method stub
				Calendar c=	Calendar.getInstance();
			
			
				new DatePickerDialog(MainActivity.this,          //获取当前日期
						new  DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year, int month,
                      	int day) {
							 	// TODO Auto-generated method stub
								editTime.setText(year+"-"+(month+1)+"-"+day);
							}
				},c.get(Calendar.YEAR),c.get(Calendar.MONTH),
				   c.get(Calendar.DAY_OF_MONTH)).show();
			}
						
			
		});
	}
		
	

	/**
	 * initAll方法

	 */
	private void initAll() {





		// TODO Auto-generated method stub
		btnTime=(Button) this.findViewById(R.id.btnTime);
		btnAdd=(Button) this.findViewById(R.id.btnAdd);
		btnQuery=(Button) this.findViewById(R.id.btnQuery);
		editBody=(EditText) this.findViewById(R.id.editBody);
		editSubject=(EditText) this.findViewById(R.id.editSubject);
		editTime=(EditText) this.findViewById(R.id.editTime);
		lvResult=(ListView) this.findViewById(R.id.lvResult);
		
	}   
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {





		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class MyOnClickListener implements OnClickListener{




		@Override
		public void onClick(View v) {



			// TODO Auto-generated method stub
			mydbHelper=new MyDatabaseHelper(MainActivity.this,"memento.db",null,1);
			SQLiteDatabase db=mydbHelper.getReadableDatabase();
			String subStr=editSubject.getText().toString();
			String babyStr=editBody.getText().toString();
			String dateStr=editTime.getText().toString();
			
			switch(v.getId()){
			case R.id.btnAdd:
				title.setVisibility(View.INVISIBLE);
				addMemento(db,subStr,babyStr,dateStr);
				Toast.makeText(MainActivity.this, "添加备忘录成功", 1000).show();
				lvResult.setAdapter(null);
				break;
			case R.id.btnQuery:
				title.setVisibility(View.VISIBLE);
				Cursor cursor=queryMemento(db,subStr,babyStr,dateStr);
				SimpleCursorAdapter resultAdapter=new SimpleCursorAdapter(
						MainActivity.this,R.layout.result,cursor,
						new String[]{
								"_id","subject","baby","date"},
								new int[]{
								R.id.memento_num,R.id.memento_subject,R.id.memento_bady,R.id.memento_date
						});
				lvResult.setAdapter(resultAdapter);
				break;
				default:
					break;
				
			}
			
			
		}
	}

		private Cursor queryMemento(SQLiteDatabase db, String subject,


				String	bady, String date) {
			// TODO Auto-generated method stub
			Cursor cursor=db.rawQuery("select * from memento_tb where subject, select like?and bady like? and date like?",new String[]{"%"+subject+"%","%"+bady+"%","%"+date+"%"});							
			return cursor;
		}

		public void addMemento(SQLiteDatabase db, String subject,


				String	bady, String date) {
			// TODO Auto-generated method stub
			db.execSQL("insert into memnto_tb values(null,?,?,?)",new String[]{
					subject,bady,date});
			this.editSubject.setText("");
			this.editBody.setText("");
			this.editTime.setText("");
		}
		
		
		protected void onDestory(){



			if(mydbHelper!=null){
				mydbHelper.close();
			}
		}
	}



