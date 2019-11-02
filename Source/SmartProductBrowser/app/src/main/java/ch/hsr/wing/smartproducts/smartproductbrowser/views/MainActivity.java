package ch.hsr.wing.smartproducts.smartproductbrowser.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ch.hsr.wing.smartproducts.R;

public class MainActivity extends AppCompatActivity implements IFragmentViewer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableToolbar();
        BottomNavigationView navigation = registerNavigation();
        initView(navigation);
    }

    private void enableToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BottomNavigationView registerNavigation(){
        BottomNavigationView navigation = findViewById(R.id.navigation_menu);
        navigation.setOnNavigationItemSelectedListener(new NavigationListener(this));
        return navigation;
    }

    private void initView(BottomNavigationView navigation){
        int selected = navigation.getSelectedItemId();
        navigation.setSelectedItemId(selected);
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
