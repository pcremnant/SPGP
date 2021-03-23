package kr.ac.kpu.game.s2016180021.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox firewallCheckBox;
    private TextView outTextView;
    private EditText userEditText;
    private TextView userTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firewallCheckBox = findViewById(R.id.checkBox);
        outTextView = findViewById(R.id.textView);
        userEditText = findViewById(R.id.editText);
        userTextView = findViewById(R.id.editTextView);


        userEditText.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userTextView.setText("String length : " + s.length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void onBtnApply(View view) {
        boolean checked = firewallCheckBox.isChecked();
        String text = checked ? "Using firewall" : "Not using firewall";
        outTextView.setText(text);

        String user = userEditText.getText().toString();
        userTextView.setText("User info : " + user);
    }

    public void onCheckFirewall(View view) {
        boolean checked = firewallCheckBox.isChecked();
        String text = checked ? "Using firewall" : "Not using firewall";
        outTextView.setText(text);
    }
}