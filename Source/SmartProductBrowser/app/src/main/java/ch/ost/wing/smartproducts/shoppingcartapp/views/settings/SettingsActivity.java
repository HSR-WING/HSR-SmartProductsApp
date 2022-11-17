package ch.ost.wing.smartproducts.shoppingcartapp.views.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ch.ost.wing.smartproducts.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.enableToolbar();
        this.showSettings();
    }

    private void enableToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void showSettings(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.setting_content, new ConnectionSettingsFragment())
                .commit();
    }
}
