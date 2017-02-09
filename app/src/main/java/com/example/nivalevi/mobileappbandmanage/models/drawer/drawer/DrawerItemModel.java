package com.example.nivalevi.mobileappbandmanage.models.drawer.drawer;

/**
 * Created by nivalevi on 09/02/2017.
 */

public class DrawerItemModel {

    public enum IconPosition {
        LEFT,
        RIGHT,
    }

    public enum DrawerItemType {
        CONTACT, RATE, HELP, SETTINGS, INVITE, DASHBOARD, MYPROFILE, ABOUT, ACTIONS,
        WEEKLY_SUMMARY, ACTIONS_SUMMARY, CARE_PANEL_APP_HEADER, CARE_PANEL_APP,
        ADD_CARERECEIVER, CARE_PANEL_HELP_DESK, CARE_RECEIVER_TAB, CARE_PANEL_PHONE, TITLE
    }

    private String text;
    protected int icon;
    private DrawerItemType type;
    private IconPosition position;
    private String comment;

    public DrawerItemModel(String text, int icon, DrawerItemType type) {
        init(text, icon, type, IconPosition.LEFT, "");
    }

    public DrawerItemModel(String text, int icon, DrawerItemType type, String comment) {
        init(text, icon, type, IconPosition.LEFT, comment);
    }

    private void init(String text, int icon, DrawerItemType type, IconPosition position, String comment) {
        this.text = text;
        this.icon = icon;
        this.type = type;
        this.position = position;
        this.comment = comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public IconPosition getPosition() {
        return position;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public DrawerItemType getType() {
        return type;
    }

    public void setType(DrawerItemType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
