package com.example.nguyenquanganh_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.nguyenquanganh_ktra2_bai2.model.Customer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    TextView txtTong;
    RecyclerviewAdapter recyclerviewAdapter;
    ArrayList<Customer> list = new ArrayList<>();
    SQLiteCustomerHelper sqLiteCustomerHelper = new SQLiteCustomerHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcv_customer);
        fab = findViewById(R.id.fab_add);
        txtTong = findViewById(R.id.txt_tong);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewAdapter = new RecyclerviewAdapter(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        int tong = 0;
        super.onResume();
        list = sqLiteCustomerHelper.getAll();
        recyclerviewAdapter.setList(list);
        recyclerView.setAdapter(recyclerviewAdapter);
        for(Customer c: list) {
            if(c.isLuggage()) {
                tong++;
            }
        }
        txtTong.setText("Tong hanh ly: " + tong);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Customer> customers = sqLiteCustomerHelper.getCustomerByName(newText);
                recyclerviewAdapter.setList(customers);
                recyclerView.setAdapter(recyclerviewAdapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}