package petrovitch.bstu.shoestore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import petrovitch.bstu.shoestore.AppRepository;
import petrovitch.bstu.shoestore.Entities.Product;
import petrovitch.bstu.shoestore.R;

import petrovitch.bstu.shoestore.Activities.MainActivity;


public class NewTovarActivity extends AppCompatActivity {

    private AppRepository appRepository;
    EditText nameText;
    EditText priceText;
    EditText imageName;
    EditText decriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        nameText = findViewById(R.id.name);
        priceText = findViewById(R.id.price);
        imageName = findViewById(R.id.image_name);
        decriptionText = findViewById(R.id.description);

        appRepository = new AppRepository(getApplication());

    }

    public void addProductClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Product product = new Product(String.valueOf(nameText.getText()),
                Double.parseDouble(String.valueOf(priceText.getText())),
                String.valueOf(imageName.getText()),
                String.valueOf(decriptionText.getText()));
        appRepository.addProduct(product);
        startActivity(intent);
    }
}
