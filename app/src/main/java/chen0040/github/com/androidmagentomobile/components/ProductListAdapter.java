package chen0040.github.com.androidmagentomobile.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.chen0040.magento.models.Product;

import java.util.ArrayList;

import chen0040.github.com.androidmagentomobile.R;

/**
 * Created by chen0 on 4/7/2017.
 */

public class ProductListAdapter extends ArrayAdapter<Product> {
    public ProductListAdapter(Context context, ArrayList<Product> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvPrice);
        // Populate the data into the template view using the data object
        tvName.setText(product.getName());
        tvHome.setText("$" + product.getPrice());
        // Return the completed view to render on screen
        return convertView;
    }
}
