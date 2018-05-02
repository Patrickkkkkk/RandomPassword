package edu.illinois.cs.cs125.randompassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;

import com.wx.pwd.CheckStrength;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText lengthInput = (EditText) findViewById(R.id.LengthA);
        final EditText containInput = (EditText) findViewById(R.id.ContainA);
        final CheckBox upper = (CheckBox) findViewById(R.id.ifUpper);
        final CheckBox character = (CheckBox) findViewById(R.id.ifCharacter);
        final Button done = (Button) findViewById(R.id.Finish);
        final TextView password = (TextView) findViewById(R.id.PasswordA);
        final TextView strengthA = (TextView) findViewById(R.id.StrengthA);

        done.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int length = Integer.parseInt(lengthInput.getText().toString());
                String contain = containInput.getText().toString();
                boolean ifUpper = upper.isChecked();
                boolean ifCharacter = character.isChecked();
                MainActivity temp = new MainActivity();
                String mima = temp.creator(length, contain, ifUpper, ifCharacter);
                int strength = CheckStrength.checkPasswordStrength(mima);
                if (strength >= 0 && strength <= 3) {
                    String result = "easy";
                    strengthA.setText(result);
                } else if (strength >= 4 && strength <= 6) {
                    String result = "medium";
                    strengthA.setText(result);
                } else if (strength >= 7 && strength <= 9) {
                    String result = "strong";
                    strengthA.setText(result);
                } else if (strength >= 10) {
                    String result = "very strong";
                    strengthA.setText(result);
                }
                password.setText(mima);
            }
        });
    }

    //This is the function we use to create our randomly generated password.
    public String creator(int length, String contain, boolean ifUpper, boolean ifCharacter) {
        Random rand = new Random();
        int currLength = length - contain.length();
        char[] result = new char[currLength];
        String last = contain;
        for (int i = 0; i < result.length; i++) {
            if (ifUpper && ifCharacter) {
                result[i] = (char) (rand.nextInt(94) + 33);
            } else if (ifUpper) {
                int k = rand.nextInt(3);
                if (k == 0) {
                    result[i] = (char) (rand.nextInt(10) + 48);
                } else if (k == 1) {
                    result[i] = (char) (rand.nextInt(26) + 65);
                } else if (k == 2) {
                    result[i] = (char) (rand.nextInt(26) + 97);
                }
            } else if (ifCharacter) {
                int k = rand.nextInt(2);
                if (k == 0) {
                    result[i] = (char) (rand.nextInt(32) + 33);
                } else {
                    result[i] = (char) (rand.nextInt(36) + 91);
                }
            }
            else {
                int k = rand.nextInt(2);
                if (k == 0) {
                    result[i] = (char) (rand.nextInt(10) + 48);
                } else {
                    result[i] = (char) (rand.nextInt(26) + 97);
                }
            }
            last = last + result[i];
        }
        return last;
    }
}