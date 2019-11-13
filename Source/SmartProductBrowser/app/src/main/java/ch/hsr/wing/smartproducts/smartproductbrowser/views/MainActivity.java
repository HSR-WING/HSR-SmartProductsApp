package ch.hsr.wing.smartproducts.smartproductbrowser.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.di.DI;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.navigation.IFragmentViewer;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.navigation.NavigationListener;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements IFragmentViewer {

    BottomNavigationView _navigation;

    @Inject
    NavigationListener _navigationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DI.container(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableToolbar();
        this._navigation = registerNavigation();
    }

    private void enableToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private BottomNavigationView registerNavigation(){
        BottomNavigationView navigation = findViewById(R.id.navigation_menu);
        navigation.setOnNavigationItemSelectedListener(this._navigationListener);
        return navigation;
    }

    @Override
    protected void onResume(){
        super.onResume();
        this._navigationListener.setViewer(this);
        int view = this._navigationListener.getCurrentView();
        this._navigation.setSelectedItemId(view);
    }

    @Override
    protected void onPause(){
        this._navigationListener.removeViewer();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean show(Fragment fragment) {
        try{
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.app_content, fragment);
            transaction.commit();
        } catch (Exception ex){
            return false;
        }
        return true;
    }


}
