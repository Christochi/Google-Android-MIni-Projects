/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     int quantity = 0;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price;

        CheckBox toppingChoice = (CheckBox) findViewById(R.id.check_box);
        boolean hasWhippedCream = toppingChoice.isChecked();
        //Log.v("MainActivity", "has Whipped Cream: " +hasWhippedCream);

        CheckBox addChocolate = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = addChocolate.isChecked();

        EditText name = (EditText) findViewById(R.id.name_view);
        Editable displayText = name.getText();

        price = calculatePrice(hasWhippedCream, hasChocolate);
        //displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, displayText));
        String bodyMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, displayText);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order For " + displayText);
        intent.putExtra(Intent.EXTRA_TEXT, bodyMessage );

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int pricePerCup = 5;
        int whippedCream = 1;
        int chocolate = 2;


        if (hasWhippedCream)
        {
            pricePerCup += whippedCream;
        }

        if (hasChocolate)
        {
            pricePerCup += chocolate;
        }

        return pricePerCup * quantity;
    }

    /**
     * Create summary of the order
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, Editable text)
    {

        String priceMessage = "Name: " + text;
        priceMessage = priceMessage + "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage = priceMessage + "\nAdd Chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage = priceMessage + "\nTotal: $" + price;
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        display(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}