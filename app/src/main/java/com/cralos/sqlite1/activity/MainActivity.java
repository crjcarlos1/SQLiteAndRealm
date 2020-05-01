package com.cralos.sqlite1.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.sqlite1.R;
import com.cralos.sqlite1.fragmentrealm.view.FragmentRealm;
import com.cralos.sqlite1.fragmentsqlite.view.FragmentSQLite;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //showFragmentSQLite();
        initRealm();
        showFragmentRealm();
    }

    private void showFragmentSQLite() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentSQLite.TAG);
        transaction.add(R.id.conteinerFragments, new FragmentSQLite(), FragmentSQLite.TAG).commit();
    }

    private void showFragmentRealm() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentRealm.TAG);
        transaction.add(R.id.conteinerFragments, new FragmentRealm(), FragmentRealm.TAG).commit();
    }

    private void initRealm(){
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name("ejemplo.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

}
