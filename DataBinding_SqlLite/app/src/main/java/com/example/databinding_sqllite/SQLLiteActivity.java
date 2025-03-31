package com.example.databinding_sqllite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.databinding_sqllite.adapter.NotesAdapter;
import com.example.databinding_sqllite.handler.DatabaseHandler;
import com.example.databinding_sqllite.model.NotesModel;

import java.util.ArrayList;

public class SQLLiteActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;
    private SQLLiteActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqllite);
        listView = (ListView) findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(this, R.layout.row_notes, arrayList);
        listView.setAdapter(adapter);
        InitDatabaseSQLite();
        //createDatabaseSQLite();
        databaseSQLite();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAddNotes) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }
    private void DialogThem() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_notes);
        //anh xp trong dieleg
        EditText editText = dialog.findViewById(R.id.editTextName);
        Button buttonAdd = dialog.findViewById(R.id.buttonThem);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);
        //bắt sự kiện cho nút thêm về huy
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(SQLLiteActivity.this, "Vui lòng nhập tên Notes", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHandler.QueryData("INSERT INTO Notes VALUES (null,'" + name + "')");
                    Toast.makeText(SQLLiteActivity.this, "Đã thêm Notes", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    databaseSQLite();//gại hàn load lại dữ liệu
                }
            }
        });
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void DialogCapNhatNotes (String name, int id) {
        Dialog dialog = new Dialog(SQLLiteActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_notes);
        //ành xo
        EditText editText = dialog.findViewById(R.id.editTextName);
        Button buttonEdit = dialog.findViewById(R.id.buttonThem);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);
        TextView textView = dialog.findViewById(R.id.textView);
        editText.setText(name);
        textView.setText("CẬP NHẬT NOTES");
        buttonEdit.setText("Cập nhật");

        // bắt sự kiện
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                databaseHandler.QueryData("UPDATE Notes SET NameNotes = '" + name + "' WHERE Id = '" + id + "'");
                Toast.makeText(SQLLiteActivity.this, "Đã cập nhật Notes thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                databaseSQLite();//goi hàm Loud lại đữ Liệu
            }
        });
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void DialogDelete(String name, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa Notes" +name+" này không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHandler. QueryData("DELETE FROM Notes WHERE Id='"+ id +"'");
                Toast.makeText(SQLLiteActivity.this, "Đã xóa Notes "+name+" thành công", Toast.LENGTH_SHORT).show();
                databaseSQLite();//gọi hàm load lại dữ liệu

            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        builder.show();
    }
    private void createDatabaseSQLite() {
        // thêm dữ liệu vào bảng
        databaseHandler.QueryData("INSERT INTO Notes VALUES (null, 'Vi dụ SQLite 1')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES (null, 'Ví dụ SQLite 2')");

    }

    private void InitDatabaseSQLite() {
        // khởi tạo database
        databaseHandler = new DatabaseHandler(this, "notes.sqlite", null, 1);
        // tạo bằng Notes
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes (Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(208))");
    }

    private void databaseSQLite() {
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        //Lấy dữ liệu
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            int id=cursor.getInt(0);
            arrayList.add(new NotesModel(id, name));
        }
        Log.d("Test", "SQLLITE");
        adapter.notifyDataSetChanged();
    }
}