package vn.com.greenacademy.shopping.Handle.HandleData.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

import static android.provider.Telephony.Carriers.PASSWORD;

/**
 * Created by ADMIN on 8/20/2017.
 */

public class MySQLite extends SQLiteOpenHelper {
    // ten db
    public static String DB_NAME = "SQLite.db";
    // ten bang
    public static String PRODUCT_TABLE = "TAIKHOAN";

    // ten cot
    // bang tai khoan
    public static String ID = "ID";
    public static String NAME = "NAME";

    // Cau lenh SQLite  tao bang Tai Khoan
    private String CREATE_TABLE_PN = "CREATE TABLE " + PRODUCT_TABLE + " ( "
            + ID +  " text primary key, "
            + NAME + " text)";


    // tao bien chua du lieu tam
    ContentValues contentValues;
    public void taodulieuGia (SQLiteDatabase db){
        // goi ham tao bang tai khoan
        db.execSQL(CREATE_TABLE_PN);
        // khoi tao gia tri ban dau cho bien tam
        contentValues = new ContentValues();
        // them du lieu vao bien tam
        contentValues.put(ID, "-1");
        contentValues.put(NAME, "^$*!@#^*(+)(*&^%$");
        // goi ham ghi du lieu vao bang
        db.insert(PRODUCT_TABLE, null, contentValues);

//        // khoi tao gia tri ban dau cho bien tam
//        contentValues = new ContentValues();
//        // them du lieu vao bien tam
//        contentValues.put(ID, "2");
//        contentValues.put(NAME, "employee001");
//        // goi ham ghi du lieu vao bang
//        db.insert(PRODUCT_TABLE, null, contentValues);
//
//        // khoi tao gia tri ban dau cho bien tam
//        contentValues = new ContentValues();
//        // them du lieu vao bien tam
//        contentValues.put(ID, "3");
//        contentValues.put(NAME, "employee002");
//        // goi ham ghi du lieu vao bang
//        db.insert(PRODUCT_TABLE, null, contentValues);
//
//        // khoi tao gia tri ban dau cho bien tam
//        contentValues = new ContentValues();
//        // them du lieu vao bien tam
//        contentValues.put(ID, "4");
//        contentValues.put(NAME, "employee003");
//        // goi ham ghi du lieu vao bang
//        db.insert(PRODUCT_TABLE, null, contentValues);
    }

    public MySQLite (Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        taodulieuGia(db);
    }

    // ham them lay san pham
    public void setSanpham (SanPham sanPham){
        // khoi tao doi tuong SQLite
        SQLiteDatabase db  = getWritableDatabase();
        // tao bien luu du lieu tam dang ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, sanPham.getIdSanPham());
        contentValues.put(NAME, sanPham.getTenSanPham());
        // luu du lieu vao database
        db.insert(PRODUCT_TABLE, null, contentValues);
        // dong ket noi database
        db.close();
    }

    // ham lay san pham
    public ArrayList<SanPham> getSanPham(){
        // khai bao bien tra ve
        ArrayList<SanPham> result = new ArrayList<>();
        // tao doi tuong SQLite
        SQLiteDatabase db = getReadableDatabase();
        // khai bao bien android de doc du lieu
        String sql = "select * from " + PRODUCT_TABLE;
        //  nhan ket qua tra ve tung hang
        Cursor cursor = db.rawQuery(sql, null);
        // tao bien dang tai khoan
        SanPham sanPham;
        while (cursor.moveToNext()){
            // khai bao gia tri ban dau cho taiKhoan
            sanPham = new SanPham();
            // cai dat du lieu cho bien tai khoan
            sanPham.setIdSanPham(Long.parseLong(cursor.getString(cursor.getColumnIndex(ID))));
            sanPham.setTenSanPham(cursor.getString(cursor.getColumnIndex(NAME)));
            // them doi tuong
            result.add(sanPham);
        }
        // dong ket noi
        cursor.close();
        db.close();
        // xuat du lieu
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
