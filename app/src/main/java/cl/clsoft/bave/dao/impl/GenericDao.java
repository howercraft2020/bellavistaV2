package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.dao.db.DataBaseHelper;
import cl.clsoft.bave.dao.rowmapper.RowMapper;

public class GenericDao<T> {

    private static final String TAG = "GenericDao";

    public void insert(String table, ContentValues values) {
        SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();
        db.insert(table, null, values);
    }

    public void update(String table, ContentValues values, String whereSql, Object... parameters) {
        SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();
        if (parameters != null) {
            String[] args = new String[parameters.length];
            int index = 0;
            for (Object parameter : parameters) {
                args[index] = parameter.toString();
            }
            db.update(table, values, whereSql, args);
        } else {
            db.update(table, values, null, null);
        }
    }

    public void delete(String table, String whereSql, Object... parameters) {
        SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();
        if (parameters != null) {
            String[] args = new String[parameters.length];
            int index = 0;
            for (Object parameter : parameters) {
                args[index] = parameter.toString();
            }
            db.delete(table, whereSql, args);
        } else {
            db.delete(table, null, null);
        }
    }

    public T selectOne(String sql, RowMapper<T> rm, Object... parameters) {
        SQLiteDatabase db = DataBaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = null;
        T output = null;
        if (parameters != null) {
            String[] args = new String[parameters.length];
            int index = 0;
            for (Object parameter : parameters) {
                args[index] = parameter.toString();
                index = index + 1;
            }
            cursor = db.rawQuery(sql, args);
        } else {
            cursor = db.rawQuery(sql, null);
        }

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    output = rm.mapRow(cursor, cursor.getPosition());
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return output;

    }

    public List<T> selectMany(String sql, RowMapper<T> rm, Object... parameters) {
        SQLiteDatabase db = DataBaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = null;
        List<T> output = new ArrayList<>();
        if (parameters != null) {
            String[] args = new String[parameters.length];
            int index = 0;
            for (Object parameter : parameters) {
                args[index] = parameter.toString();
            }
            cursor = db.rawQuery(sql, args);
        } else {
            cursor = db.rawQuery(sql, null);
        }

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        T entity = rm.mapRow(cursor, cursor.getPosition());
                        output.add(entity);
                    } while(cursor.moveToNext());
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return output;
    }

    private SQLiteStatement getStatement(SQLiteDatabase db, String sql, Object... parameters) {
        SQLiteStatement stmt = db.compileStatement(sql);
        int index = 1;
        for (Object parameter : parameters) {
            if (parameter instanceof Long) {
                stmt.bindLong(index, (Long) parameter);
            }
            if (parameter instanceof Double) {
                stmt.bindDouble(index, (Double) parameter);
            }
            if (parameter instanceof String) {
                stmt.bindString(index, (String) parameter);
            }
            index++;
        }
        return stmt;
    }
}
