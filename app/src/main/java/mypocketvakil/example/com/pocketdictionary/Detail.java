package mypocketvakil.example.com.pocketdictionary;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {
    TextView detail_meaning,detail_synonym,detail_antonym,detail_translation,detail_title;
    SQLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    int id;
    String title,meaning,synonym,antonym,translation;
    ImageView heart_green,heart;
    boolean message=true;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        SQLITEHELPER = new SQLiteHelper(this);
        heart_green=(ImageView)findViewById(R.id.detail_favorite);
        heart=(ImageView)findViewById(R.id.detail_favorite_white);
        heart_green.setVisibility(View.GONE);
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(value==1)
                {
                    heart.setVisibility(View.GONE);
                    heart_green.setVisibility(View.VISIBLE);

                    value=0;
                    update();
                }

//                heart.setVisibility(View.VISIBLE);
//                heart_green.setVisibility(View.GONE);

            }
        });

        heart_green.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        if(value==0)
        {
            heart.setVisibility(View.VISIBLE);
            heart_green.setVisibility(View.GONE);
            value=1;
            updateReverse();
        }

//        heart_green.setVisibility(View.VISIBLE);
//        heart.setVisibility(View.GONE);
    }
});

        detail_title=(TextView)findViewById(R.id.detail_title);

        detail_antonym=(TextView)findViewById(R.id.detail_antonym);
        detail_synonym=(TextView)findViewById(R.id.detail_synonym);
        detail_meaning=(TextView)findViewById(R.id.detail_meaning);
        detail_translation=(TextView)findViewById(R.id.detail_tranlation);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        id=id+1;
       fetch();

    }
    @Override
    public void onBackPressed() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Detail.this,MainActivity.class);
                startActivity(i);

                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                finish();
            }
        }, 100);
    }

    private void updateReverse() {
        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_Favorites, 1);
        long update=SQLITEDATABASE.update(SQLiteHelper.TABLE_NAME,values,"id="+id,null);
        Toast.makeText(this,"Remove from Favourites",Toast.LENGTH_SHORT).show();

    }

    private void update() {
        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_Favorites, 0);
        long update=SQLITEDATABASE.update(SQLiteHelper.TABLE_NAME,values,"id="+id,null);
        Toast.makeText(this,"Added to Favourites",Toast.LENGTH_SHORT).show();
    }

    private void fetch() {


        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM Pocketdict WHERE id="+id, null);
        if (cursor.moveToFirst())
        {
            title=cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Title));
            meaning=cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Meaning));
            synonym=cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Synonyms));
            antonym=cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Antonyms));
            translation=cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Translation));
            value=cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Favorites));
            if(value==0)
            {
                heart_green.setVisibility(View.VISIBLE);
                heart.setVisibility(View.GONE);
            }

        }




        detail_title.setText(title);
        detail_translation.setText(translation);
        detail_meaning.setText(meaning);
        detail_antonym.setText(antonym);
        detail_synonym.setText(synonym);

    }
}
