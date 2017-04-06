package mypocketvakil.example.com.pocketdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SQLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteAdapter ListAdapter ;
    String SQLiteQuery ;
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> Title_ArrayList = new ArrayList<String>();
    ArrayList<String> Meaning_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;
    static int count=0;
    ArrayList<HashMap<String, String>> listDataHeader;
    private ArrayList<HashMap<String, List<String>>> listDataChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LISTVIEW=(ListView)findViewById(R.id.list);

        SQLITEHELPER = new SQLiteHelper(this);
         /* values.put(KEY_Title,"Consideration");
        values.put(KEY_Meaning,"The thought process of considering, of taking everything into account");
        values.put(KEY_Synonyms,"thought, deliberation, reflection, contemplation,");
        values.put(KEY_Translation,"considération");
        values.put(KEY_Antonyms,"disregard, thoughtlessness");
        values.put(KEY_Favorites,1);
        */
      DBCreate();
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("firstRUN", true);
        if(isFirstRun)
{
    Model m1= new Model(10,"Consideration",
            "The thought process of considering, of taking everything into account",
            "thought, deliberation, reflection, contemplation",
            "considération",
            "disregard, thoughtlessness",
            1
    );
    Model m2=new Model(20,"Rejuvinate",
            "To render young again",
            "revive, revitalize, renew, regenerate, restore",
            "rajeunir",
            "damage,kill,lose",
            1);
    Model m3=new Model(30,"Stereotype","a widely held but fixed and oversimplified image or idea of a particular type of person or thing",
            "standard/conventional image, received idea",
            "stéréotype",
            "unconventional, original, fresh",
            1);
    Model m4=new Model(30,"Affection","a gentle feeling of fondness or liking",
            "fondness, love, liking, endearment, feeling, sentiment, tenderness, warmth, ",
            "affection",
            "abomination, hate, hatred, loathing, rancor",
            1);
    Model m5=new Model(30,"Dormant","Inactive, asleep, supended, having normal physical functions suspended or slowed down for a period of time; in or as if in a deep sleep.",
            "asleep, sleeping, slumbering, resting, reposing, drowsing, comatose, supine",
            "dôrmənt",
            "awake, active",
            1);
    Model m6=new Model(30,"Fulmination","an expression of vehement protest, a violent explosion or a flash like lightning",
            "standard/conventional image, received idea",
            "fulmination",
            "complimant, praise, defense",
            1);
    Model m7=new Model(30,"Ambiguity","the quality of being open to more than one interpretation; inexactness.",
            "ambivalence, equivocation; obscurity, vagueness, abstruseness, doubtfulness ",
            "ambiguïté",
            "unambiguousness, transparency",
            1);
    Model m8=new Model(30,"Ulterior","existing beyond what is obvious or admitted; intentionally hidden",
            "secondary, underlying, undisclosed, undivulged, unexpressed, unapparent",
            "ultérieur",
            "intentionally concealed, to decieve",
            1);
    Model m9=new Model(30,"Ravening","(of a ferocious wild animal) extremely hungry and hunting for prey.",
            "rapacious, voracious, acquisitive",
            "raviner",
            "unacquisitive, abstemious,unaggressive",
            1);
    long insert1 = SQLITEHELPER.Insert(m1);
    long insert2 = SQLITEHELPER.Insert(m2);
    long insert3 = SQLITEHELPER.Insert(m3);
    long insert4 = SQLITEHELPER.Insert(m4);
    long insert5 = SQLITEHELPER.Insert(m5);
    long insert6 = SQLITEHELPER.Insert(m6);
    long insert7 = SQLITEHELPER.Insert(m7);
    long insert8 = SQLITEHELPER.Insert(m8);
    long insert9 = SQLITEHELPER.Insert(m9);


    SharedPreferences.Editor editor = wmbPreference.edit();
    editor.putBoolean("firstRUN", false);
    editor.commit();
    Toast.makeText(MainActivity.this, "DAta ent.", Toast.LENGTH_LONG).show();
}



        List<Model> allToDos = SQLITEHELPER.fetch();
        for (Model todo : allToDos) {
            Log.d("ToDo", todo.getTitle());
        }
       fetch();

          LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {


                  new Handler().postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          Intent intent=new Intent(MainActivity.this,Detail.class);
                          intent.putExtra("id",i);
                          startActivity(intent);
                          overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                          finish();
                      }
                  },100);
              }
          });



        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void fetch() {
        listDataHeader=new ArrayList<HashMap<String, String>>();
        listDataChild=new ArrayList<HashMap<String, List<String>>>();
        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM pocketdict", null);

        ID_ArrayList.clear();
        Title_ArrayList.clear();
        Meaning_ArrayList.clear();


        if (cursor.moveToFirst()) {
            do {



                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)));

                Title_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Title)));

                Meaning_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Meaning)));



            } while (cursor.moveToNext());
        }

        ListAdapter = new SQLiteAdapter(MainActivity.this,

                ID_ArrayList,
                Title_ArrayList,
                Meaning_ArrayList


        );

        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();
    }

    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("Dictionary", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS Pocketdict(id INTEGER PRIMARY KEY NOT NULL, title VARCHAR, meaning VARCHAR, synonyms VARCHAR, translation VARCHAR, antonyms VARCHAR, favorites INTEGER);");
    }

    public void SubmitData2SQLiteDB(){
        SQLITEDATABASE= SQLITEHELPER.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(SQLITEHELPER.KEY_Title,"consider");
        values.put(SQLITEHELPER.KEY_Meaning,"dfdvfd");
        values.put(SQLITEHELPER.KEY_Synonyms,"dsvfv");
        values.put(SQLITEHELPER.KEY_Translation,"dsvffd");
        values.put(SQLITEHELPER.KEY_Antonyms,"fdvgb");
        values.put(SQLITEHELPER.KEY_Favorites,"fdvfd");
        long newRowId = SQLITEDATABASE.insert(SQLITEHELPER.TABLE_NAME, null, values);

        Toast.makeText(MainActivity.this, "DAta entered!!!!!!", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this,Favourites.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    finish();
                }
            },200);

            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
