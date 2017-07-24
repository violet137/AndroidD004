package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Model.Item_recyclerView_magazine;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class AdapterMagazineRecyclerView extends RecyclerView.Adapter<MagazineHolder>{
    Context context;
    LayoutInflater mLayoutInflater;
    View.OnClickListener onClickListener;
    int positionViewPagger;

    public AdapterMagazineRecyclerView(Context context, View.OnClickListener onClickListener, int positionViewPagger) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.onClickListener=onClickListener;
        this.positionViewPagger = positionViewPagger;
    }

    @Override
    public MagazineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_magazine, parent, false);
        return new MagazineHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MagazineHolder holder, int position) {
        String nameViewPager = null;
        switch (positionViewPagger){
            case SupportKeyList.Magazine_ALL:
                nameViewPager = "ALL";
                break;
            case SupportKeyList.Magazine_FASHION:
                nameViewPager = "FASHION";
                break;
            case SupportKeyList.Magazine_BEAUTY:
                nameViewPager = "BEAUTY";
                break;
            case SupportKeyList.Magazine_CULURE:
                nameViewPager = "CULURE";
                break;
            case SupportKeyList.Magazine_VIDEO:
                nameViewPager = "VIDEO";
                break;
            default:
                break;
        }
        Item_recyclerView_magazine item_recyclerView_magazine = new Item_recyclerView_magazine();
        item_recyclerView_magazine.setId(position);
        item_recyclerView_magazine.setName(nameViewPager);
        holder.itemView.setTag(item_recyclerView_magazine);
        holder.itemView.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return  6;    }
}
class MagazineHolder extends RecyclerView.ViewHolder {
    TextView tvTime,tvType,tvName, tvDetail;
    ImageView imageView;
    public MagazineHolder(View itemView) {
        super(itemView);
        tvTime = (TextView) itemView.findViewById(R.id.tvTime_item_magazine);
        tvType = (TextView) itemView.findViewById(R.id.tvType_item_magazine);
        tvName = (TextView) itemView.findViewById(R.id.tvName_item_magazine);
        tvDetail = (TextView) itemView.findViewById(R.id.tvDetail_item_magazine);

        imageView = (ImageView) itemView.findViewById(R.id.imageView_item_magazine);
    }

}
