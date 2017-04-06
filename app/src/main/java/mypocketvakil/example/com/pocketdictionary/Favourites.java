package mypocketvakil.example.com.pocketdictionary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Favourites extends AppCompatActivity {
    TextView favorite_text,favorite_summary;
    ListView listView;
    SQLiteDatabase SQLITEDATABASE;
    SQLiteHelper SQLITEHELPER;
    Cursor cursor;
    TextView textView;
    SQLiteAdapter ListAdapter ;
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> Title_ArrayList = new ArrayList<String>();
    ArrayList<String> Meaning_ArrayList = new ArrayList<String>();
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        SQLITEHELPER=new SQLiteHelper(this);
        textView=(TextView)findViewById(R.id.textview);
        textView.setVisibility(View.GONE);

        listView=(ListView)findViewById(R.id.favorite_list);
        fetch();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id= Integer.parseInt(ID_ArrayList.get(i));
                id=id-1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(Favourites.this,Detail.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                    }
                },100);
            }
        });

    }
    @Override
    public void onBackPressed() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Favourites.this,MainActivity.class);
                startActivity(i);

                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                finish();
            }
        }, 100);
    }

    private void fetch() {
        SQLITEDATABASE=SQLITEHELPER.getWritableDatabase();
        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM Pocketdict WHERE favorites=0", null);
        ID_ArrayList.clear();
        Title_ArrayList.clear();
        Meaning_ArrayList.clear();

        if (cursor.getCount()==0)
        {

            textView.setVisibility(View.VISIBLE);
        }
        if (cursor.moveToFirst()) {
            do {



                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)));

                Title_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Title)));

                Meaning_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Meaning)));



            } while (cursor.moveToNext());
        }
        ListAdapter = new SQLiteAdapter(Favourites.this,

                ID_ArrayList,
                Title_ArrayList,
                Meaning_ArrayList


        );

        listView.setAdapter(ListAdapter);

        cursor.close();
    }
}
