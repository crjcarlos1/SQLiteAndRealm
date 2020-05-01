package com.cralos.sqlite1.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AlumnosHelper extends SQLiteOpenHelper {

    public static final String CREATE_DB = "CREATE TABLE "
            + AlumnosManager.TABLA_ALUMNOS + " ("
            + AlumnosManager.COLUMNA_MATRICULA + " INTEGER PRIMARY KEY,"
            + AlumnosManager.COLUMNA_NOMBRES + " TEXT NOT NULL,"
            + AlumnosManager.COLUMNA_CARRERA + " TEXT NOT NULL, "
            + AlumnosManager.COLUMNA_SEMESTRE + " INTEGER NOT NULL)";

    public AlumnosHelper(@Nullable Context context) {
        super(context, AlumnosManager.NOMBRE_DB, null, AlumnosManager.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
