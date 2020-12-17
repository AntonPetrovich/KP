package petrovitch.bstu.shoestore;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import petrovitch.bstu.shoestore.Entities.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int layout;
    private List<Product> products;
    private Context appContext;

    public ProductAdapter(Context context, int resource, List<Product> products) {

        super(context, resource, products);
        this.products = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        appContext = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView priceView = (TextView) view.findViewById(R.id.price);

        Product product = products.get(position);

        InputStream inputStream = null;
        try{
            inputStream = appContext.getAssets().open(product.getImagePath());
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

        nameView.setText(product.getProductName());
        priceView.setText(String.valueOf(product.getPrice()) + "$");

        return view;
    }
}
