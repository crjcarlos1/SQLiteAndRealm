package com.cralos.sqlite1.fragment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cralos.sqlite1.R;
import com.cralos.sqlite1.fragment.interfaces.SQLitePresenter;
import com.cralos.sqlite1.fragment.interfaces.SQLiteView;
import com.cralos.sqlite1.fragment.presenters.SQLitePresenterImpl;

public class FragmentSQLite extends Fragment implements View.OnClickListener, SQLiteView {
    public static final String TAG = FragmentSQLite.class.getSimpleName();

    private EditText edtStudentID, edtStudentNames, edtSemester, edtCareer;

    private Button btnInsert, btnConsultByID, btnConsultAll, btnDeleteStudentByName, btnConsultIdsGreaterThan5,
            btnConsultAllCarlosIDSgreaterThan5, btnSumaMatriculas, btnOrder, btnMaximo;

    private TextView txvResult;

    private SQLitePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sqlite, container, false);
        initFragmentSQLite(view);
        return view;
    }

    private void initFragmentSQLite(View view) {
        txvResult = (TextView) view.findViewById(R.id.txvResult);

        edtStudentID = (EditText) view.findViewById(R.id.edtStudentID);
        edtStudentNames = (EditText) view.findViewById(R.id.edtStudentNames);
        edtSemester = (EditText) view.findViewById(R.id.edtSemester);
        edtCareer = (EditText) view.findViewById(R.id.edtCareer);

        btnInsert = (Button) view.findViewById(R.id.btnInsert);
        btnConsultByID = (Button) view.findViewById(R.id.btnConsultByID);
        btnConsultAll = (Button) view.findViewById(R.id.btnConsultAll);
        btnDeleteStudentByName = (Button) view.findViewById(R.id.btnDeleteStudentByName);
        btnConsultIdsGreaterThan5 = (Button) view.findViewById(R.id.btnConsultIdsGreaterThan5);
        btnConsultAllCarlosIDSgreaterThan5 = (Button) view.findViewById(R.id.btnConsultAllCarlosIDSgreaterThan5);
        btnSumaMatriculas = (Button) view.findViewById(R.id.btnSumaMatriculas);
        btnOrder = (Button) view.findViewById(R.id.btnOrder);
        btnMaximo = (Button) view.findViewById(R.id.btnMaximo);

        btnInsert.setOnClickListener(this);
        btnConsultByID.setOnClickListener(this);
        btnConsultAll.setOnClickListener(this);
        btnDeleteStudentByName.setOnClickListener(this);
        btnConsultIdsGreaterThan5.setOnClickListener(this);
        btnConsultAllCarlosIDSgreaterThan5.setOnClickListener(this);
        btnSumaMatriculas.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
        btnMaximo.setOnClickListener(this);

        presenter = new SQLitePresenterImpl(this, getContext());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsert:
                presenter.insertarEstudiante(edtStudentID.getText().toString(), edtStudentNames.getText().toString(), edtSemester.getText().toString(), edtCareer.getText().toString());
                break;
            case R.id.btnConsultByID:
                presenter.consultarAlumnoPorMatricula(edtStudentID.getText().toString());
                break;
            case R.id.btnConsultAll:
                presenter.consultarTodo();
                break;
            case R.id.btnDeleteStudentByName:
                presenter.borrarAlumnoPorNombre(edtStudentNames.getText().toString());
                break;
            case R.id.btnConsultIdsGreaterThan5:
                presenter.consultarMatriculasMayoresA5();
                break;
            case R.id.btnConsultAllCarlosIDSgreaterThan5:
                presenter.consultarAlosCarlosConMatriculaMayorA5();
                break;
            case R.id.btnSumaMatriculas:
                presenter.sumarMatriculas();
                break;
            case R.id.btnOrder:
                presenter.order();
                break;
            case R.id.btnMaximo:
                presenter.maximo();
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showStudents(String students) {
        txvResult.setText(students);
    }

}
