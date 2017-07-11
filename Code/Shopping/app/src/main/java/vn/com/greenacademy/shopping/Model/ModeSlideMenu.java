package vn.com.greenacademy.shopping.Model;

import java.io.Serializable;

/**
 * Created by GIT on 3/11/2017.
 */

public class ModeSlideMenu implements Serializable {
    int icon;
    String ten;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}

