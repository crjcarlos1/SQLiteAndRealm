package com.cralos.sqlite1.fragmentsqlite.interactors;

import android.content.ContentValues;
import android.content.Context;

import com.cralos.sqlite1.fragmentsqlite.interfaces.SQLiteInteractor;
import com.cralos.sqlite1.fragmentsqlite.interfaces.SQLitePresenter;
import com.cralos.sqlite1.fragmentsqlite.models.Student;
import com.cralos.sqlite1.fragmentsqlite.sqlite.AlumnosManager;

import java.util.List;

public class SQLiteInteractorImpl implements SQLiteInteractor {

    private SQLitePresenter presenter;
    private Context context;
    private AlumnosManager alumnosManager;

    public SQLiteInteractorImpl(SQLitePresenter presenter, Context MYcontext) {
        this.presenter = presenter;
        this.context = MYcontext;
    }

    @Override
    public void insertarEstudiante(String matricula, String nombres, String semestre, String carrera) {
        validateStudentInsertData(matricula, nombres, semestre, carrera);
    }

    private void validateStudentInsertData(String matricula, String nombres, String semestre, String carrera) {
        if (matricula == null || matricula.length() == 0) {
            presenter.setMessageToView("Ingresa matrícula");
            return;
        }
        if (nombres == null || nombres.length() == 0) {
            presenter.setMessageToView("Ingresa nombres de alumno");
            return;
        }
        if (semestre == null || semestre.length() == 0) {
            presenter.setMessageToView("Ingresa semestre de alumno");
            return;
        }
        if (carrera == null || semestre.length() == 0) {
            presenter.setMessageToView("Ingresa semestre de alumno");
            return;
        }
        int matriculaInt = Integer.parseInt(matricula);
        int semestreInt = Integer.parseInt(semestre);
        buildInsertValues(matriculaInt, nombres, semestreInt, carrera);
    }

    private void buildInsertValues(int matricula, String nombres, int semestre, String carrera) {
        ContentValues values = new ContentValues();
        values.put(AlumnosManager.COLUMNA_MATRICULA, matricula);
        values.put(AlumnosManager.COLUMNA_NOMBRES, nombres);
        values.put(AlumnosManager.COLUMNA_SEMESTRE, semestre);
        values.put(AlumnosManager.COLUMNA_CARRERA, carrera);
        insertStudent(values);
    }

    private void insertStudent(ContentValues values) {
        alumnosManager = AlumnosManager.getInstance(context);
        boolean insert = alumnosManager.insertStudent(values);
        if (insert) presenter.setMessageToView("insert ok");
        else presenter.setMessageToView("insert failture");
    }

    @Override
    public void consultarTodo() {
        alumnosManager = AlumnosManager.getInstance(context);
        List<Student> students = alumnosManager.consultarEstudiantes();
        if (students != null && students.size() > 0) {
            String stringStudents = "";
            for (Student student : students) {
                stringStudents += student.getMatricula() + ", " + student.getNombres() + ", " + student.getCarrera() + ", " + student.getSemestre() + "\n";
            }
            presenter.setEstudiantes(stringStudents);
        } else {
            presenter.setMessageToView("Sin estudiantes");
        }
    }

    @Override
    public void consultarAlumnoPorMatricula(String matricula) {
        if (matricula == null || matricula.length() == 0) {
            presenter.setMessageToView("ingresa matricula de alumno");
        } else {
            String[] args = new String[]{matricula};
            alumnosManager = AlumnosManager.getInstance(context);
            Student student = alumnosManager.consultarEstudiantePorMatricula(args);
            if (student != null) {
                presenter.setMessageToView(student.getMatricula() + ", " + student.getNombres() + ", " + student.getCarrera()+ ", " + student.getSemestre() );
            } else {
                presenter.setMessageToView("No se encontro estudiante con esa matricula");
            }
        }
    }

    @Override
    public void borrarAlumnoPorNombre(String name) {
        if (name == null || name.length() == 0) {
            presenter.setMessageToView("Ingresa nombre para eliminar de la base de datos");
        } else {
            alumnosManager = AlumnosManager.getInstance(context);
            String[] args = new String[]{name};
            presenter.setMessageToView(alumnosManager.eliminarAlumnoPorNombre(args));
        }
    }

    @Override
    public void consultarMatriculasMayoresA5() {
        alumnosManager = AlumnosManager.getInstance(context);
        List<Student> students = alumnosManager.consultarMatriculasMayoresA5();
        if (students != null && students.size() > 0) {
            String alumnos = "";
            for (Student student : students) {
                alumnos += student.getMatricula() + ", " + student.getNombres() + ", " + student.getCarrera() + ", " + student.getSemestre() + "\n";
            }
            presenter.setEstudiantes(alumnos);
        } else {
            presenter.setMessageToView("No se encontraron alumnos");
        }
    }

    @Override
    public void consultarAlosCarlosConMatriculaMayorA5() {
        alumnosManager = AlumnosManager.getInstance(context);
        List<Student> students = alumnosManager.consultarAlosCarlosConMatriculaMayorA5();
        if (students != null && students.size() > 0) {
            String result = "";
            for (Student student : students) {
                result += student.getMatricula() + ", " + student.getNombres() + ", " + student.getCarrera() + ", " + student.getSemestre() + "\n";
            }
            presenter.setEstudiantes(result);
        } else {
            presenter.setMessageToView("No se encontraron alumnos");
        }
    }

    @Override
    public void sumarMatriculas() {
        alumnosManager = AlumnosManager.getInstance(context);
        String result = "suma: " + alumnosManager.sumarMatriculas();
        presenter.setMessageToView(result);
    }

    @Override
    public void order() {
        alumnosManager = AlumnosManager.getInstance(context);
        List<Student> students = alumnosManager.obtenerEstudiantesDesscendenteEMatricula();
        if (students != null && students.size() > 0) {
            String results = "";
            for (Student student : students) {
                results += student.getMatricula() + ", " + student.getNombres() + ", " + student.getCarrera() + ", " + student.getSemestre() + "\n";
            }
            presenter.setEstudiantes(results);
        } else {
            presenter.setMessageToView("Sin resultados");
        }
    }

    @Override
    public void maximo() {
        alumnosManager = AlumnosManager.getInstance(context);
        int matriculaMaxima = alumnosManager.obtenerMatriculaMaxima();
        if (matriculaMaxima < 0) {
            presenter.setMessageToView("sin resultados");
        } else {
            presenter.setMessageToView("Matricula mayor: " + matriculaMaxima);
        }
    }


}
