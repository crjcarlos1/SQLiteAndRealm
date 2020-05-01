package com.cralos.sqlite1.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cralos.sqlite1.models.Student;

import java.util.ArrayList;
import java.util.List;

public class AlumnosManager {

    private static Context context;
    private static AlumnosHelper helper;
    private static SQLiteDatabase database;
    private static AlumnosManager instance = null;

    public static final int VERSION = 1;
    public static final String NOMBRE_DB = "alumnos.sqlite";
    public static final String TABLA_ALUMNOS = "tAlumnos";
    public static final String COLUMNA_MATRICULA = "cMatricula";
    public static final String COLUMNA_NOMBRES = "cNombres";
    public static final String COLUMNA_CARRERA = "cCarrera";
    public static final String COLUMNA_SEMESTRE = "cSemestre";

    public static synchronized AlumnosManager getInstance(Context myContext) {
        if (instance == null) {
            instance = new AlumnosManager();
            context = myContext;
        }
        return instance;
    }

    public boolean insertStudent(ContentValues values) {
        if (openDataBaseWrite()) {
            long result = database.insert(TABLA_ALUMNOS, null, values);
            closeDataBase();
            if (result == -1) return false;
            else return true;
        } else {
            return false;
        }
    }

    public List<Student> consultarEstudiantes() {
        List<Student> estudiantes = new ArrayList<>();
        if (openDataBaseRead()) {
            Cursor cursor = database.rawQuery("SELECT * FROM " + TABLA_ALUMNOS, null);
            if (cursor.moveToFirst()) {
                do {
                    int matricula = cursor.getInt(cursor.getColumnIndex(COLUMNA_MATRICULA));
                    String nombres = cursor.getString(cursor.getColumnIndex(COLUMNA_NOMBRES));
                    int semestre = cursor.getInt(cursor.getColumnIndex(COLUMNA_SEMESTRE));
                    String carrera = cursor.getString(cursor.getColumnIndex(COLUMNA_CARRERA));
                    Student student = new Student(nombres, carrera, String.valueOf(matricula), String.valueOf(semestre));
                    estudiantes.add(student);
                } while (cursor.moveToNext());
                closeDataBase();
                return estudiantes;
            } else {
                closeDataBase();
                return null;
            }
        } else {
            return null;
        }
    }

    public List<Student> consultarMatriculasMayoresA5() {
        if (openDataBaseRead()) {
            String query = "SELECT * FROM " + TABLA_ALUMNOS + " WHERE " + COLUMNA_MATRICULA + " > 5";
            Cursor cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                List<Student> listaAlumnos = new ArrayList<>();
                do {
                    String nombres = cursor.getString(cursor.getColumnIndex(AlumnosManager.COLUMNA_NOMBRES));
                    String carrera = cursor.getString(cursor.getColumnIndex(AlumnosManager.COLUMNA_CARRERA));
                    int matricula = cursor.getInt(cursor.getColumnIndex(AlumnosManager.COLUMNA_MATRICULA));
                    int semestre = cursor.getInt(cursor.getColumnIndex(AlumnosManager.COLUMNA_SEMESTRE));
                    Student student = new Student(nombres, carrera, String.valueOf(matricula), String.valueOf(semestre));
                    listaAlumnos.add(student);
                } while (cursor.moveToNext());
                closeDataBase();
                return listaAlumnos;
            } else {
                closeDataBase();
                return null;
            }
        } else {
            return null;
        }
    }

    public List<Student> obtenerEstudiantesDesscendenteEMatricula() {
        if (openDataBaseRead()) {
            String query = "SELECT *FROM " + TABLA_ALUMNOS + " ORDER BY " + COLUMNA_MATRICULA + " DESC";
            Cursor cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                List<Student> estudiantes = new ArrayList<>();
                do {
                    String nombres = cursor.getString(cursor.getColumnIndex(AlumnosManager.COLUMNA_NOMBRES));
                    String carrera = cursor.getString(cursor.getColumnIndex(AlumnosManager.COLUMNA_CARRERA));
                    int matricula = cursor.getInt(cursor.getColumnIndex(AlumnosManager.COLUMNA_MATRICULA));
                    int semestre = cursor.getInt(cursor.getColumnIndex(AlumnosManager.COLUMNA_SEMESTRE));
                    Student student = new Student(nombres, carrera, String.valueOf(matricula), String.valueOf(semestre));
                    estudiantes.add(student);
                } while (cursor.moveToNext());
                closeDataBase();
                return estudiantes;
            } else {
                closeDataBase();
                return null;
            }
        } else {
            return null;
        }
    }

    public int obtenerMatriculaMaxima() {
        if (openDataBaseRead()) {
            String query = "SELECT MAX(" + COLUMNA_MATRICULA + ") FROM " + TABLA_ALUMNOS;
            Cursor cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                int matriculaMaxima = cursor.getInt(0);
                closeDataBase();
                return matriculaMaxima;
            } else {
                closeDataBase();
                return -100;
            }
        } else {
            return -100;
        }
    }

    public List<Student> consultarAlosCarlosConMatriculaMayorA5() {
        if (openDataBaseRead()) {
            String query = "SELECT * FROM " + TABLA_ALUMNOS + " WHERE " + COLUMNA_NOMBRES + " = 'carlos' AND " + COLUMNA_MATRICULA + " > 5";
            Cursor cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                List<Student> students = new ArrayList<>();
                do {
                    String nombres = cursor.getString(cursor.getColumnIndex(AlumnosManager.COLUMNA_NOMBRES));
                    String carrera = cursor.getString(cursor.getColumnIndex(AlumnosManager.COLUMNA_CARRERA));
                    int matricula = cursor.getInt(cursor.getColumnIndex(AlumnosManager.COLUMNA_MATRICULA));
                    int semestre = cursor.getInt(cursor.getColumnIndex(AlumnosManager.COLUMNA_SEMESTRE));
                    Student student = new Student(nombres, carrera, String.valueOf(matricula), String.valueOf(semestre));
                    students.add(student);
                } while (cursor.moveToNext());
                closeDataBase();
                return students;
            } else {
                closeDataBase();
                return null;
            }
        } else {
            return null;
        }
    }

    public int sumarMatriculas() {
        if (openDataBaseRead()) {
            String query = "SELECT SUM(" + COLUMNA_MATRICULA + ") FROM " + TABLA_ALUMNOS;
            Cursor cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                int sum = cursor.getInt(0);
                closeDataBase();
                return sum;
            } else {
                closeDataBase();
                return -1;
            }
        } else {
            return -1;
        }
    }

    public Student consultarEstudiantePorMatricula(String[] args) {
        if (openDataBaseRead()) {
            String query = "SELECT *FROM " + TABLA_ALUMNOS + " WHERE " + COLUMNA_MATRICULA + " = ? ";
            Cursor cursor = database.rawQuery(query, args);
            if (cursor.moveToFirst()) {
                int matricula = cursor.getInt(cursor.getColumnIndex(AlumnosManager.COLUMNA_MATRICULA));
                String nombres = cursor.getString(cursor.getColumnIndex(AlumnosManager.COLUMNA_NOMBRES));
                String carrera = cursor.getString(cursor.getColumnIndex(AlumnosManager.COLUMNA_CARRERA));
                int semestre = cursor.getInt(cursor.getColumnIndex(AlumnosManager.COLUMNA_SEMESTRE));
                Student student = new Student(nombres, carrera, String.valueOf(matricula), String.valueOf(semestre));
                closeDataBase();
                return student;
            } else {
                closeDataBase();
                return null;
            }
        } else {
            return null;
        }
    }

    public String eliminarAlumnoPorNombre(String[] args) {
        if (openDataBaseWrite()) {
            int result = database.delete(TABLA_ALUMNOS, COLUMNA_NOMBRES + " = ? ", args);
            if (result == 1) {
                return "Se elimino a " + args[0];
            } else {
                return "Error al eliminar alumno";
            }
        } else {
            return "No se pudo abrir la base de datos";
        }
    }

    private static boolean openDataBaseWrite() {
        helper = new AlumnosHelper(context);
        database = helper.getWritableDatabase();
        if (database != null) return true;
        else return false;
    }

    private static boolean openDataBaseRead() {
        helper = new AlumnosHelper(context);
        database = helper.getReadableDatabase();
        if (database != null) return true;
        else return false;
    }

    private static void closeDataBase() {
        database.close();
    }

}
