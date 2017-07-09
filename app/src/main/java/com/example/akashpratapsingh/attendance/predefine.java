package com.example.akashpratapsingh.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class predefine extends AppCompatActivity {
    EditText subject;
    EditText present;
    EditText total;
    Button button;
    databasehelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predefine);
        subject=(EditText)findViewById(R.id.subject);
        present=(EditText)findViewById(R.id.present);
        total=(EditText)findViewById(R.id.total);
        button=(Button)findViewById(R.id.insert);
        mydb=databasehelper.getInstance(predefine.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("abcd");
                update.subjects.add(subject.getText().toString());
                mydb.insert_data(subject.getText().toString(),Integer.parseInt(present.getText().toString()),Integer.parseInt(total.getText().toString()));
                subject.setText("");
                present.setText("");
                total.setText("");
            }
        });

    }
}
