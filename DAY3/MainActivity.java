package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    SQLiteDatabase sd;
    Cursor c;
    Intent intent;
    Button btnAdd;
    String title, text,result;
    ListView list;
    LinearLayout layout;
    SimpleCursorAdapter adapter;

    @Override
    protected void onStart() {
        c = sd.query("note",null,null,null,null,null,null);
        adapter = new SimpleCursorAdapter(this, R.layout.line,c,new String[]{"_id","title"},new int[]{R.id._id,R.id.title});
        list.setAdapter(adapter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        c.close();

    }

    class MyDialogListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(i==DialogInterface.BUTTON_POSITIVE){

                TranslateAnimation ta = new TranslateAnimation(0,1500,0,0);
                ta.setDuration(1000);
                ta.setFillAfter(true);

                ta.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationStart(Animation animation) {

                    }
                    public void onAnimationEnd(Animation animation) {
                        String query = "delete from note where _id ="+result+";";
                        sd.execSQL(query);
                        Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                        c = sd.query("note",null,null,null,null,null,null);
                        adapter.changeCursor(c);

                    }
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                layout.startAnimation(ta);


            }else if(i==DialogInterface.BUTTON_NEGATIVE){
                Toast.makeText(MainActivity.this, "취", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sd=openOrCreateDatabase("memo.db",0,null);

        String schema = "create table note (_id integer primary key autoincrement, title text not null, body text not null);";
        try {
            sd.execSQL(schema);
        }catch (Exception ignore){
            Log.d("%%%%%%%%","db error");
        }
//        ContentValues values = new ContentValues();
//        values.put("title","SS");
//        values.put("body","ㅎㅇ");
//        sd.insert("note",null,values);
        setContentView(R.layout.mainactivity);
        list = findViewById(R.id.list);

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,SubActivity.class);
                intent.putExtra("ACTION","WRITE");
                startActivity(intent);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                layout = (LinearLayout)view;
                TextView t = (TextView) layout.getChildAt(0);
                result = t.getText().toString();
                c.moveToFirst();
                while(c.isAfterLast()==false){
                    String _id=c.getString(0);
                    if(_id.equals(result)){
                        title =c.getString(1);
                        text = c.getString(2);
                    }
                    c.moveToNext();
                }
                Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this,SubActivity.class);
                intent.putExtra("ACTION","READ");
                intent.putExtra("TITLE",title);
                intent.putExtra("TEXT",text);

                startActivity(intent);

            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                layout = (LinearLayout)view;
                TextView t = (TextView) layout.getChildAt(0);
                result = t.getText().toString();

                MyDialogListener md = new MyDialogListener();
                new AlertDialog.Builder(MainActivity.this).setMessage("지울까요?")
                        .setPositiveButton("Yes",md).setNegativeButton("NO",md).show();



                Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                //intent.putExtra("ACTION","WRITE");
                return true;
            }
        });



    }
}