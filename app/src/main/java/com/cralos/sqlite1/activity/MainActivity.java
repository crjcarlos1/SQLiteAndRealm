package com.cralos.sqlite1.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.sqlite1.R;
import com.cralos.sqlite1.fragment.view.FragmentSQLite;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragmentSQLite();
    }

    private void showFragmentSQLite() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentSQLite.TAG);
        transaction.add(R.id.conteinerFragments, new FragmentSQLite(), FragmentSQLite.TAG).commit();
    }
}
