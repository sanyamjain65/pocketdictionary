package mypocketvakil.example.com.pocketdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanyam jain on 06-02-2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="Dictionary";

    public static final String KEY_ID="id";

    public static final String TABLE_NAME="Pocketdict";

    public static final String KEY_Title="title";

    public static final String KEY_Meaning="meaning";

    public static final String KEY_Synonyms="synonyms";
    public static final String KEY_Translation="translation";
    public static final String KEY_Antonyms="antonyms";
    public static final String KEY_Favorites="favorites";

  //  private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
//            + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_Title
//            + " TEXT," + KEY_Meaning + " TEXT," + KEY_Synonyms
//            + " Text," +KEY_Translation + "TEXT,"+KEY_Antonyms + "TEXT," + KEY_Favorites + "INTEGER" + ")";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public long Insert(Model m){
        SQLiteDatabase db=this.getWritableDatabase();

//        ContentValues values= new ContentValues();
        ContentValues values = new ContentValues();
        values.put(KEY_Title, m.getTitle());
        values.put(KEY_Meaning, m.getMeaning());
        values.put(KEY_Synonyms, m.getSynonyms());
        values.put(KEY_Translation, m.getTranslation());
        values.put(KEY_Antonyms, m.getAntonyms());
        values.put(KEY_Favorites, m.getFavorites());



        long consideration=db.insert(TABLE_NAME,null,values);


        return consideration;

    }
    public List<Model> fetch(){
        List<Model> list = new ArrayList<Model>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Model td = new Model();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setTitle((c.getString(c.getColumnIndex(KEY_Title))));
                td.setMeaning(c.getString(c.getColumnIndex(KEY_Meaning)));

                // adding to todo list
                list.add(td);
            } while (c.moveToNext());
        }

        return list;
    }


}
