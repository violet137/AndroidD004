package vn.com.greenacademy.shopping.Handle.HandleUi.Model;

/**
 * Created by zzzzz on 7/29/2017.
 *
 * Thông tin item của QuickAction
 *
 */

import android.graphics.drawable.Drawable;

public class QuickActionItem {

    private int icon;
    private String iconHinh;
    private String title;
    private String actionId = null;
    private boolean sticky;

    public QuickActionItem(String actionId, String title, int icon) {

        this.title = title;
        this.icon = icon;
        this.actionId = actionId;
    }

    public QuickActionItem(String actionId, String title, String iconHinh) {

        this.title = title;
        this.iconHinh = iconHinh;
        this.actionId = actionId;
    }

//    public QuickActionItem(String actionId, String title, int i) {
//
//        this(null, null, null);
//    }

    public QuickActionItem(String actionId, String title) {

        this(actionId, title, 0);
    }

    public QuickActionItem(int icon) {

        this(null, null, icon);
    }

    public QuickActionItem(String actionId, int icon) {

        this(actionId, null, icon);
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getIconHinh() {
        return iconHinh;
    }

    public void setIconHinh(String iconHinh) {
        this.iconHinh = iconHinh;
    }

    public void setActionId(String actionId) {

        this.actionId = actionId;
    }

    public String getActionId() {

        return actionId;
    }

    public void setSticky(boolean sticky) {

        this.sticky = sticky;
    }

    public boolean isSticky() {

        return sticky;
    }

}
