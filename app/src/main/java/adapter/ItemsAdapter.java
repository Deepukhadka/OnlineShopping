package adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineshopping.DescriptionActivity;
import com.example.onlineshopping.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Item;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    Context mContext;
    List<Item> itemList;

    public ItemsAdapter(Context mContext, List<Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup,false);
        return new ItemsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder itemsViewHolder, int i) {

        final Item item = itemList.get(i);

        Resources res =  mContext.getResources();
        final int resourceId = res.getIdentifier(
                item.getImageName(), "drawable", mContext.getPackageName());

        itemsViewHolder.imgItem.setImageResource(resourceId);
        itemsViewHolder.tvItem.setText(item.getName());
        itemsViewHolder.tvPrice.setText(Integer.toString(item.getPrice()));
        itemsViewHolder.tvDescription.setText(item.getDescription());


        itemsViewHolder.imgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DescriptionActivity.class);

                intent.putExtra("image", resourceId);
                intent.putExtra("name", item.getName());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("description", item.getDescription());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgItem;
        TextView tvItem, tvPrice,tvDescription;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            tvItem = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDescription = itemView.findViewById(R.id.tvDescription);

        }
    }
}
