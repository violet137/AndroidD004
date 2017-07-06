package vn.com.greenacademy.shopping.ParData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ModelAccount;

/**
 * Created by ADMIN on 7/6/2017.
 */

public class ParGoogleID {
    String mData;

    public ParGoogleID(String mData) {
        this.mData = mData;
    }

    public ModelAccount parID() throws JSONException {
        ModelAccount result = new ModelAccount();
        JSONObject root = new JSONObject(mData);
        result.setDescription(root.getString("Description"));
        result.setStatus(root.getInt("Status"));
        result.setToken(root.getString("Token"));
        return result;
    }

}
