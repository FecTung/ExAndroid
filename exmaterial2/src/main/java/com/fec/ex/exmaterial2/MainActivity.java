package com.fec.ex.exmaterial2;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton bottomFAB;
    private MaterialButton btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomFAB = findViewById(R.id.bottomFAB);
        btnSwitch = findViewById(R.id.btnSwitch);

        bottomAppBar.replaceMenu(R.menu.main);
        btnSwitch.setOnClickListener(switchStyle);
        bottomFAB.setOnClickListener(switchStyle);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Snackbar.make(bottomAppBar, menuItem.getTitle(), Snackbar.LENGTH_SHORT).setAction("OK", switchStyle).show();
                return true;
            }
        });
    }

    private View.OnClickListener switchStyle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int currMode = bottomAppBar.getFabAlignmentMode();
            bottomAppBar.setFabAlignmentMode(currMode^1);
        }
    };
}
