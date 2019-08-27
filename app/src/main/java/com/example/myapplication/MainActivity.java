package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button encode = findViewById(R.id.encode);

        final Spinner dropdown = findViewById(R.id.spinner);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.list));
        dropdown.setAdapter(itemsAdapter);
        encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = dropdown.getSelectedItem().toString();
                EditText message = findViewById(R.id.message);
                EditText key = findViewById(R.id.key);
                TextView fart = findViewById(R.id.textView);
                String text = message.getText().toString();
                int k = Integer.parseInt(key.getText().toString());
                String encoded = "failed";
                if(item.equals("encode")) {
                     encoded = encode(text, k);
                }
                else if(item.equals("decode")){
                     encoded = encode(text, -k);
                }
                fart.setText("your code is: "+encoded);
            }
        });




    }
    public  String encode(String message, int keyVal){
        String output = "";
        char key = (char)keyVal;
        for(int x = 0; x<message.length();x++){
            char input = message.charAt(x);
            if(input>='A' && input<='Z'){
                input += key;
                if(input>'Z')
                    input -= 26;
                if(input<'A')
                    input += 26;
            }
            else if(input >= 'a' && input <= 'z'){
                input += key;
                if(input > 'z')
                    input -= 26;
                if(input < 'a')
                    input +=26;
            }
            else if(input >= '0' && input <= '9'){
                input -= (keyVal % 10);
                if(input > '9')
                    input -= 10;
                if(input <'0')
                    input += 10;
            }
            output += input;
        }
        return output;
    }
}
