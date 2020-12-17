package petrovitch.bstu.shoestore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import petrovitch.bstu.shoestore.AppRepository;
import petrovitch.bstu.shoestore.Entities.Product;
import petrovitch.bstu.shoestore.ProductAdapter;
import petrovitch.bstu.shoestore.R;

import java.util.ArrayList;
import java.util.List;

import petrovitch.bstu.shoestore.R;

public class SearchingActivity extends AppCompatActivity {

    AppRepository appRepository;
    EditText lowRangeText;
    EditText highRangeText;
    EditText searchText;
    ProductAdapter productAdapter;
    ListView productList;
    public List<Product> listProduct;
    public List<Product> newProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        productList = findViewById(R.id.productList);
        searchText = findViewById(R.id.searchTextt);
        lowRangeText = findViewById(R.id.lowRangeText);
        highRangeText = findViewById(R.id.highRangeText);

        appRepository = new AppRepository(getApplication());
        newProductList = new ArrayList<>();
    }

    public void searchClick(View view){
        try{
            listProduct = appRepository.getAllProducts();
            newProductList.clear();
            for (Product product: listProduct){
                if(product.getProductName().equals(searchText.getText().toString())){
                    newProductList.add(product);
                }
            }
            productAdapter = new ProductAdapter(getApplicationContext(), R.layout.list_item, newProductList);
            productList.setAdapter(productAdapter);
        } catch (Exception e) {
            Log.d("searchCheck", "searchClick: " + e.getMessage());
        }
    }

    public void selectClick(View view){
        listProduct = appRepository.getAllProducts();
        newProductList.clear();
        for (Product product: listProduct){
            if((product.getPrice() > Double.parseDouble(lowRangeText.getText().toString())) && (product.getPrice() < Double.parseDouble(highRangeText.getText().toString()))){
                newProductList.add(product);
            }
        }
        productAdapter = new ProductAdapter(getApplicationContext(), R.layout.list_item, newProductList);
        productList.setAdapter(productAdapter);

    }
}