package com.example.myintentapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int REQUEST_CODE = 100;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        Button btnMoveWithData = findViewById(R.id.btn_move_activity_data);
        Button btnDialNumber = findViewById(R.id.btn_dial_number);
        Button btnMoveForResult = findViewById(R.id.btn_move_for_result);

        btnMoveActivity.setOnClickListener(this);
        btnMoveWithData.setOnClickListener(this);
        btnDialNumber.setOnClickListener(this);
        btnMoveForResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_move_activity:
                Intent pindahActivity = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(pindahActivity);
                break;
            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveWithDataIntent);
                break;
            case R.id.btn_dial_number:
                String num = "082131609462";
                Intent moveDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+num));
                startActivity(moveDial);
                break;
            case R.id.btn_move_for_result:
                Intent moveForResult = new Intent(MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(moveForResult, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       if (requestCode == REQUEST_CODE && resultCode == MoveForResultActivity.RESULT_CODE) {
//            String val = data.getStringExtra(ResultActivity.EXTRA_SELECTED_VALUE);
//            tvRes.setText(String.format("Nama = %s", val));
            int val = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
            tvResult.setText(String.format("Result = %s", val));
        }
    }
}
