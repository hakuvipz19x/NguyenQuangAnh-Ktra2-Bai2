package com.example.nguyenquanganh_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nguyenquanganh_ktra2_bai2.model.Customer;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText edtName, edtDate;
    Button btnGetDate, btnAdd, btnCancel;
    Spinner spinnerDeparture;
    CheckBox cbHasLuggage;
    SQLiteCustomerHelper sqLiteCustomerHelper = new SQLiteCustomerHelper(this);
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtName = findViewById(R.id.edt_add_name);
        edtDate = findViewById(R.id.edt_add_date);
        btnGetDate = findViewById(R.id.btn_add_getDate);
        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel_add);
        spinnerDeparture = findViewById(R.id.spinner_departure);
        cbHasLuggage = findViewById(R.id.cb_hasLuggage);

        String[] listDeparture = {"Hà Nội", "Đà Nẵng", "TP HCM", "Nha Trang"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, listDeparture);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerDeparture.setAdapter(arrayAdapter);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                dialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String departure = (String) spinnerDeparture.getSelectedItem();
                String date = edtDate.getText().toString();
                Boolean hasLuggage;
                if (cbHasLuggage.isChecked()) {
                    hasLuggage = true;
                } else {
                    hasLuggage = false;
                }
                Customer customer = new Customer(name, departure, date, hasLuggage);
                sqLiteCustomerHelper.addCustomer(customer);
                finish();
            }
        });
    }
}