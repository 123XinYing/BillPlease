package sg.edu.rp.c346.id22011117.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView amt;
    TextView people;
    ToggleButton svs;
    ToggleButton gst;
    TextView discount;
    RadioGroup rg;
    ToggleButton split;
    ToggleButton reset;
    TextView totalBill;
    TextView eachPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.textViewAmount); // linking to the ID
        people = findViewById(R.id.textViewPeople);
        svs = findViewById(R.id.toggleButtonNoSVS);
        gst = findViewById(R.id.toggleButtonGST);
        discount = findViewById(R.id.textViewDiscount);
        rg = findViewById(R.id.radioGroup);
        split = findViewById(R.id.toggleButtonSplit);
        reset = findViewById(R.id.toggleButtonReset);
        totalBill = findViewById(R.id.textViewTotalBill);
        eachPay = findViewById(R.id.textViewEachPays);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                if (amt.getText().toString().length() != 0 && people.getText().toString().length() != 0) {
                    double newAmount = 0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmount = Double.parseDouble(amt.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmount = Double.parseDouble(amt.getText().toString()) * 0.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmount = Double.parseDouble(amt.getText().toString()) * 0.07;
                    } else {
                        newAmount = Double.parseDouble(amt.getText().toString()) * 0.17;
                    }

                    if (discount.getText().toString().length() != 0) {
                        newAmount *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }
                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmount));
                    int numberPeople = Integer.parseInt(people.getText().toString());
                    if (numberPeople != 1)
                        eachPay.setText("Each Pays: $" + String.format("%.2f", newAmount / numberPeople));
                    else
                        eachPay.setText("Each Pays: $" + newAmount);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amt.setText("");
                people.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });
    }
}
