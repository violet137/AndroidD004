package vn.com.greenacademy.shopping.ParData;

import org.json.JSONException;
import org.json.JSONObject;

import vn.com.greenacademy.shopping.Model.Md_Account;

/**
 * Created by ADMIN on 7/6/2017.
 */

public class Par_GoogleID {
    String mData;

    public Par_GoogleID(String mData) {
        this.mData = mData;
    }

    public Md_Account parID() throws JSONException {
        Md_Account result = new Md_Account();
        JSONObject root = new JSONObject(mData);
        result.setDescription(root.getString("Description"));
        result.setStatus(root.getInt("Status"));
        result.setToken(root.getString("Token"));
        return result;
    }

}
