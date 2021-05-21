package com.example.nguyenquanganh_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyenquanganh_ktra2_bai2.model.Customer;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {
    EditText edtName, edtDate;
    Button btnGetDate, btnUpdate, btnCancel;
    Spinner spinnerDeparture;
    CheckBox cbHasLuggage;
    int mYear, mMonth, mDay;
    Button btnDelete;
    SQLiteCustomerHelper sqLiteCustomerHelper = new SQLiteCustomerHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btnDelete = findViewById(R.id.btn_delete);
        edtName = findViewById(R.id.edt_update_name);
        edtDate = findViewById(R.id.edt_update_date);
        cbHasLuggage = findViewById(R.id.cb_update_hasLuggage);
        btnUpdate = findViewById(R.id.btn_update);
        btnGetDate = findViewById(R.id.btn_update_getDate);
        btnCancel = findViewById(R.id.btn_cancel_update);
        spinnerDeparture = findViewById(R.id.spinner_update_departure);

        Intent intent = getIntent();
        Customer customer = (Customer) intent.getSerializableExtra("update_customer");
        edtName.setText(customer.getName());
        edtDate.setText(customer.getDate());
        if (customer.isLuggage()) {
            cbHasLuggage.setChecked(true);
            btnDelete.setEnabled(false);
        }

        String[] listDeparture = {"Hà Nội", "Đà Nẵng", "TP HCM", "Nha Trang"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, listDeparture);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerDeparture.setAdapter(arrayAdapter);


        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(UpdateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtDate.setText(mDay + "/" + (month + 1) + "/" + mYear);
                            }
                        }, mYear, mMonth, mDay);

                dialog.show();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteCustomerHelper.deleteCustomer(customer.getId());
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String departure = (String) spinnerDeparture.getSelectedItem();
                String date = edtDate.getText().toString();
                Boolean hasLuggage;
                if(cbHasLuggage.isChecked()) {
                    hasLuggage = true;
                } else {
                    hasLuggage = false;
                }

//                Customer customer1 = new Customer(name, departure, date, hasLuggage);
                int changedRows = sqLiteCustomerHelper.updateCustomer(new Customer(
                        customer.getId(), name, departure,date, hasLuggage
                ));

                if(changedRows > 0) {
                    Toast.makeText(getApplicationContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Cap nhat that bai", Toast.LENGTH_SHORT).show();

                }
                finish();
            }
        });
    }
}