package petrovitch.bstu.shoestore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import petrovitch.bstu.shoestore.AppRepository;
import petrovitch.bstu.shoestore.Entities.Product;
import petrovitch.bstu.shoestore.ProductAdapter;
import petrovitch.bstu.shoestore.R;
import petrovitch.bstu.shoestore.UserInfo;

import java.util.List;

import static petrovitch.bstu.shoestore.R.*;


public class MainActivity extends AppCompatActivity {

    private AppRepository repository;

    public List<Product> listProduct;
    ListView productsList;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        productsList = findViewById(id.productList);

        repository = new AppRepository(getApplication());
        Log.d("UserRole", "onCreate: " + UserInfo.role);
        Intent intentEdit = new Intent(this, ChangeProductActivity.class);
        Intent intentInfo = new Intent(this, InformationActivity.class);
        listProduct = repository.getAllProducts();
        productAdapter = new ProductAdapter(getApplicationContext(), layout.list_item, listProduct);
        productsList.setAdapter(productAdapter);
        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = listProduct.get((int)id);
                if (UserInfo.role.equals("ADMIN")){
                    intentEdit.putExtra("productId", product.getPid());
                    intentEdit.putExtra("productName", product.getProductName());
                    intentEdit.putExtra("productPrice", product.getPrice());
                    intentEdit.putExtra("productImagePath", product.getImagePath());
                    intentEdit.putExtra("productDescription", product.getDescription());
                    intentEdit.putExtra("UserRole", UserInfo.role);
                    startActivity(intentEdit);
                } else if (UserInfo.role.equals("USER")){
                    intentEdit.putExtra("productId", product.getPid());
                    intentInfo.putExtra("productName", product.getProductName());
                    intentInfo.putExtra("productPrice", product.getPrice());
                    intentInfo.putExtra("productImagePath", product.getImagePath());
                    intentInfo.putExtra("productDescription", product.getDescription());
                    startActivity(intentInfo);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        try {
            if (UserInfo.role.equals("ADMIN")) {
                menu.getItem(0).setVisible(true);
            } else {
                menu.getItem(0).setVisible(false);
            }
        } catch (Exception e) {
            Log.d("MenuOptiona", "onCreateOptionsMenu: " + e.getMessage());

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case id.new_product:
                Intent intent = new Intent(this, NewTovarActivity.class);
                startActivity(intent);
                return true;
            case id.search:
                Intent intent1 = new Intent(this, SearchingActivity.class);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}