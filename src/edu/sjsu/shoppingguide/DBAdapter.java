package edu.sjsu.shoppingguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_ROWID = "_sid";
	public static final String KEY_SNAME = "sname";
	public static final String KEY_STREET = "street";
	public static final String KEY_LAT = "lat";
	public static final String KEY_LON = "lon";
	public static final String KEY_CITY = "city";
	public static final String KEY_ZIP = "zip";
	public static final String KEY_STATE = "state";
	public static final String KEY_ITEMID = "itemid";
	public static final String KEY_PARKING = "parking";
	
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "MyDB";
	private static final String DATABASE_TABLE = "stores";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = 	"CREATE TABLE stores (_sid TEXT, sname TEXT, " +
			"street TEXT, lat NUMERIC, lon NUMERIC, city TEXT, zip NUMERIC, state TEXT, itemid TEXT, parking TEXT);";
	
	private final Context context;
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapter(Context ctx)
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
			+ newVersion + ", which will destroy all old data");
			
			db.execSQL("DROP TABLE IF EXISTS stores");
			onCreate(db);
		}
	}
	
	//---opens the database---
	public DBAdapter open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	//---closes the database---
	public void close()
	{
		DBHelper.close();
	}
	
	//---insert a contact into the database---
	public long insertContact(String sname, String street)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_SNAME, sname);
		initialValues.put(KEY_STREET, street);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	//---deletes a particular contact---
	public boolean deleteContact(long rowId)
	{
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	//---retrieves all the contacts---
	public Cursor getAllData()
	{
		return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_SNAME, KEY_STREET, KEY_LAT, KEY_LON, 
				KEY_CITY, KEY_ZIP, KEY_STATE, KEY_ITEMID, KEY_PARKING}, null, null, null, null, null);
	}
	
	//---retrieves a particular contact---
	public Cursor getData(long rowId) throws SQLException
	{
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_SNAME, KEY_STREET,
				KEY_LAT, KEY_LON, KEY_CITY, KEY_ZIP, KEY_STATE, KEY_ITEMID, KEY_PARKING}, 
				KEY_ROWID + "=" + rowId, null,
		null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//---updates a contact---
	public boolean updateContact(long rowId, String name, String email)
	{
		ContentValues args = new ContentValues();
		
		args.put(KEY_SNAME, name);
		args.put(KEY_STREET, email);
		
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}

}
