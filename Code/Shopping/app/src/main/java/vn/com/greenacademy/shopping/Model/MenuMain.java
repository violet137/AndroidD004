package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

/**
 * Created by ADMIN on 7/15/2017.
 */

public class MenuMain {
    ArrayList<AdvertisePhoto> advertiseMenuMains;
    String url;
    String id;
    int flag;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public ArrayList<AdvertisePhoto> getAdvertiseMenuMains() {
        return advertiseMenuMains;
    }

    public void setAdvertiseMenuMains(ArrayList<AdvertisePhoto> advertiseMenuMains) {
        this.advertiseMenuMains = advertiseMenuMains;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
