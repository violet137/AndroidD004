package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

/**
 * Created by ADMIN on 7/18/2017.
 */

public class MenuPhoto {
    ArrayList<FashionType> fashionTypeArrayList;
    int Status;
    String Description;

    public ArrayList<FashionType> getFashionTypeArrayList() {
        return fashionTypeArrayList;
    }

    public void setFashionTypeArrayList(ArrayList<FashionType> fashionTypeArrayList) {
        this.fashionTypeArrayList = fashionTypeArrayList;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
