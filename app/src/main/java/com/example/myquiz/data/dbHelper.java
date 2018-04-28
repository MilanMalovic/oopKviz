package com.example.myquiz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myquiz.Question;

import java.util.ArrayList;
import java.util.List;

import static com.example.myquiz.data.QuizContract.MovieEntry.KEY_ANSWER;
import static com.example.myquiz.data.QuizContract.MovieEntry.KEY_ID;
import static com.example.myquiz.data.QuizContract.MovieEntry.KEY_OPTA;
import static com.example.myquiz.data.QuizContract.MovieEntry.KEY_OPTB;
import static com.example.myquiz.data.QuizContract.MovieEntry.KEY_OPTC;
import static com.example.myquiz.data.QuizContract.MovieEntry.KEY_QUES;
import static com.example.myquiz.data.QuizContract.MovieEntry.TABLE_QUEST;



public class dbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    private SQLiteDatabase dbase;
    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {

        Question q1=new Question("U objektno orijentisanom programiranju klasa je:","  Vrsta objekta ", "Sablon za kreiranje objekata iste vrste ", "  Skup promenljivih koje cine stanje objekta ", "Sablon za kreiranje objekata iste vrste");
        this.addQuestion(q1);
        Question q2=new Question("Koncept nasleđivanja u objektno orijentisanom programiranju omogućava:", "Kopiranje stanja postojećeg objekta prilikom kreiranja novog", "Definisanje nove klase na osnovu definicije vec postojece", "  Kreiranje nove biblioteke koja ukljucuje sve funkcije neke postojece", "Definisanje nove klase na osnovu definicije vec postojece");
        this.addQuestion(q2);
        Question q3=new Question("U objektno orijentisanim programskim jezicima kao što su Java, C++ ili C# modifikator pristupa protected omogucava pristup clanovima klase:","  Samo u toj istoj klasi", "  Samo u klasama koje je prosiruju","Samo u toj klasi i klasama koje je prosiruju", "Samo u toj klasi i klasama koje je prosiruju");
        this.addQuestion(q3);
        Question q4=new Question("U objektno orijentisanom programiranju sledeći iskaz je istinit:", "Apstraktna klasa ne postoji u objektno orijentisanim programskim jezicima, koristi se samo u fazi OOP analize i dizajna", "Apstraktna klasa je vrsta objekta cije ponasanje se moze redefinisati u apstraktnoj klasi", "Apstraktna klasa se ne moze instancirati","Apstraktna klasa se ne moze instancirati");
        this.addQuestion(q4);
        Question q5=new Question("Ispaljeni izuzetak u Javi može biti uhvacen:","Samo u metodi u kojoj je bačen","  U bilo kojoj metodi u programu","U bilo kojoj metodi na steku poziva","U bilo kojoj metodi na steku poziva");
        this.addQuestion(q5);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
    // tasks table name
    {
    }
}

