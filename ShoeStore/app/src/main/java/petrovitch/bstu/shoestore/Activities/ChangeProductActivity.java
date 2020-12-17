package petrovitch.bstu.shoestore.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import petrovitch.bstu.shoestore.AppRepository;
import petrovitch.bstu.shoestore.Entities.Product;
import petrovitch.bstu.shoestore.R;
import petrovitch.bstu.shoestore.UserInfo;



public class ChangeProductActivity extends AppCompatActivity {

    EditText nameText;
    EditText priceText;
    EditText imageName;
    EditText decriptionText;
    private AppRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        nameText = findViewById(R.id.name);
        priceText = findViewById(R.id.price);
        imageName = findViewById(R.id.image_name);
        decriptionText = findViewById(R.id.description);

        repository = new AppRepository(getApplication());

        nameText.setText(getIntent().getStringExtra("productName"));
        priceText.setText(String.valueOf(getIntent().getDoubleExtra("productPrice", 1000)));
        imageName.setText(getIntent().getStringExtra("productImagePath"));
        decriptionText.setText(getIntent().getStringExtra("productDescription"));
        UserInfo.role = getIntent().getStringExtra("UserRole");

    }

    public void updateProductClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Product product = new Product(getIntent().getLongExtra("productId", 1), String.valueOf(nameText.getText()),
                Double.parseDouble(String.valueOf(priceText.getText())),
                String.valueOf(imageName.getText()),
                String.valueOf(decriptionText.getText()));
        repository.updateProduct(product);
        startActivity(intent);
    }

    public void deleteProductClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Product product = new Product(getIntent().getLongExtra("productId", 1), String.valueOf(nameText.getText()),
                Double.parseDouble(String.valueOf(priceText.getText())),
                String.valueOf(imageName.getText()),
                String.valueOf(decriptionText.getText()));
        repository.deleteProduct(product);
        startActivity(intent);
    }
}