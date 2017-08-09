package vn.com.greenacademy.shopping.Interface;

import android.os.Bundle;
import android.support.annotation.Nullable;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.SinglePageUiHandler;

/**
 * Created by zzzzz on 8/9/2017.
 */

public interface UpdateDataViewPagerCallBack {
    void updateData(String action, @Nullable Bundle bundle);
}
