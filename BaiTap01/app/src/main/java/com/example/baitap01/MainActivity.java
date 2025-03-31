package com.example.baitap01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ẩn thanh tiêu đề
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        // Liên kết các thành phần giao diện
        EditText etInput = findViewById(R.id.et_input);
        Button btnReverse = findViewById(R.id.btn_reverse);
        TextView tvResult = findViewById(R.id.tv_result);

        EditText etInputNumbers = findViewById(R.id.et_input_array);
        Button btnProcessNumbers = findViewById(R.id.btn_filter);
        TextView tvResultFilter = findViewById(R.id.tv_result_filter);

        btnProcessNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = etInputNumbers.getText().toString();

                if (inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter some numbers!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Chuyển chuỗi nhập vào thành mảng ArrayList
                String[] numberStrings = inputText.split(",");
                ArrayList<Integer> numbers = new ArrayList<>();
                for (String numberString : numberStrings) {
                    try {
                        numbers.add(Integer.parseInt(numberString.trim())); // Chuyển đổi thành Integer
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Invalid number format!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                ArrayList<Integer> evenNumbers = new ArrayList<>();
                ArrayList<Integer> oddNumbers = new ArrayList<>();

                for (int number : numbers) {
                    if (number % 2 == 0) {
                        evenNumbers.add(number);
                    } else {
                        oddNumbers.add(number);
                    }
                }

                // In kết quả ra Logcat
                Log.d("EvenNumbers", evenNumbers.toString());
                Log.d("OddNumbers", oddNumbers.toString());

                // Hiển thị kết quả vào TextView
                String resultText = "Số chẵn: " + evenNumbers.toString() + "\nSố lẻ: " + oddNumbers.toString();
                tvResultFilter.setText(resultText);
            }
        });


        // Xử lý nút đảo ngược chuỗi
        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = etInput.getText().toString();

                if (inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a string!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Đảo ngược và in hoa
                String[] words = inputText.split(" ");
                StringBuilder reversedText = new StringBuilder();
                for (int i = words.length - 1; i >= 0; i--) {
                    reversedText.append(words[i]).append(" ");
                }

                String result = reversedText.toString().trim().toUpperCase();
                tvResult.setText(result);

                // Hiển thị Toast
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
