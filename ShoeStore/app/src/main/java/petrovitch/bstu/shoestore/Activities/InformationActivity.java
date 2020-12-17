package petrovitch.bstu.shoestore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import petrovitch.bstu.shoestore.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class InformationActivity extends AppCompatActivity {

    ImageView imageView;
    TextView nameTextView;
    TextView priceTextView;
    TextView descriptionTextView;
    TextView countText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_product);

        imageView = findViewById(R.id.imageView);
        nameTextView = findViewById(R.id.nameTextView);
        priceTextView = findViewById(R.id.priceTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        countText = findViewById(R.id.countText);
        countText.setText(String.valueOf(0));

        nameTextView.setText(getIntent().getStringExtra("productName"));
        priceTextView.setText(String.valueOf(getIntent().getDoubleExtra("productPrice", 1000)));
        descriptionTextView.setText(getIntent().getStringExtra("productDescription"));

        InputStream inputStream = null;
        try{
            inputStream = getApplicationContext().getAssets().open(Objects.requireNonNull(getIntent().getStringExtra("productImagePath")));
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
        }
        catch (IOException e){
            Log.d("ImageCheck", "getView: " + e.getMessage());
        }
        finally {
            try{
                if(inputStream!=null)
                    inputStream.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public void minusClick(View view){
        if(Integer.parseInt(countText.getText().toString()) > 0){
            countText.setText(String.valueOf(Integer.parseInt(countText.getText().toString()) - 1));
        }
    }

    public void plusClick(View view){
        countText.setText(String.valueOf(Integer.parseInt(countText.getText().toString()) + 1));
    }

    /*public void orderClick(View view){
        CreateOrderItemDto createOrderItemDto = new CreateOrderItemDto(getIntent().getLongExtra("productId", 1), Long.parseLong(countText.getText().toString()));
        List<CreateOrderItemDto> createOrderItemsList = new ArrayList<>();
        createOrderItemsList.add(createOrderItemDto);
        CreateOrderDto createOrderDto = new CreateOrderDto(createOrderItemsList);
        Toast.makeText(this, "Order accepted", Toast.LENGTH_SHORT).show();
        Call<Void> call = orderSerice.createOrder(UserInfo.getAuthToken(), createOrderDto);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("OrderCheck", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }*/
}