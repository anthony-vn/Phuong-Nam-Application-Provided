package com.anthonycode.bookstorephuongnamaplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Insert_TheLoai;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Books;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Category;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Home;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_MyAccount;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigationView = findViewById(R.id.nv_view);
        navigationView.setItemIconTintList(null);

        mDrawerLayout = findViewById(R.id.dr_ly);
        toolbar = findViewById(R.id.tg_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerToggle = setupDrawerToggle();

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_layout, new Fragment_Home()).commit();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Class fragmentClass = null;
                //
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_layout, new Fragment_Home()).commit();
                        break;
                    case R.id.nav_theloai:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_layout, new Fragment_Category()).commit();
                        break;
                    case R.id.nav_sach:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_layout, new Fragment_Books()).commit();
                        break;
                    case R.id.nav_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_layout, new Fragment_MyAccount()).commit();
                        break;
                    default:
                        fragmentClass = Fragment_Category.class;
                }

                item.setChecked(true);
                setTitle(item.getTitle());
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(HomeActivity.this, mDrawerLayout, toolbar, R.string.Open, R.string.Close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.user){
            Toast.makeText(this, "View Account", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}