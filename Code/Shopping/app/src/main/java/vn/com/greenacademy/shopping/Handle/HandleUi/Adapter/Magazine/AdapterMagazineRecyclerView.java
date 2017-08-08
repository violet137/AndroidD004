package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.Item_recyclerView_magazine;
import vn.com.greenacademy.shopping.Model.Magazine;
import vn.com.greenacademy.shopping.Model.MagazineType;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class AdapterMagazineRecyclerView extends RecyclerView.Adapter<MagazineHolder>{
    Context context;
    LayoutInflater mLayoutInflater;
    View.OnClickListener onClickListener;
    int positionViewPagger;
    ArrayList<Magazine> magazineArrayList;
    ArrayList<MagazineType> magazineTypeArrayList;

    public AdapterMagazineRecyclerView(Context context, View.OnClickListener onClickListener,
                                       ArrayList<Magazine> magazineArrayList) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.onClickListener=onClickListener;
        this.magazineArrayList=magazineArrayList;
    }

//    public AdapterHomeRecyclerView(Context context, View.OnClickListener onClickListener, int positionViewPagger,
//                                       ArrayList<MagazineType> magazineTypeArrayList) {
//        this.context = context;
//        mLayoutInflater = LayoutInflater.from(context);
//        this.onClickListener=onClickListener;
//        this.positionViewPagger = positionViewPagger;
//        this.magazineTypeArrayList = magazineTypeArrayList;
//
//    }

    @Override
    public MagazineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_magazine, parent, false);
        return new MagazineHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MagazineHolder holder, int position) {
        String nameViewPager = null;

        final Magazine magazine = magazineArrayList.get(position);

//        nameViewPager = magazineTypeArrayList.get(positionViewPagger).getTen();

        Item_recyclerView_magazine item_recyclerView_magazine = new Item_recyclerView_magazine();

        item_recyclerView_magazine.setId(magazine.getIdTapChi());
//        item_recyclerView_magazine.setName(nameViewPager);

        holder.itemView.setTag(item_recyclerView_magazine);
        holder.itemView.setOnClickListener(onClickListener);

        setData(holder, magazine);

    }

    @Override
    public int getItemCount() {
        return magazineArrayList.size();

    }

    public void setData(MagazineHolder holder,Magazine magazine){
        holder.tvName.setText(magazine.getTen());
//        holder.tvTime.setText(magazine);
//        holder.tvName.setTypeface(Typeface.createFromAsset(context.getAssets(), "centuryschoolbook.TTF"));
        holder.tvType.setText(magazine.getLoaiTapChi());
        holder.tvDetail.setText("  "+magazine.getMoTa());

        ImageLoad imageLoad = new ImageLoad((Activity)context);
        imageLoad.load(magazine.getLinkHinh(),holder.imageView);
    }

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
