package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.dao.db.DataBaseHelper;
import cl.clsoft.bave.dao.rowmapper.RowMapper;
import cl.clsoft.bave.dao.rowmapper.common.DoubleRowMapper;
import cl.clsoft.bave.dao.rowmapper.common.LongRowMapper;
import cl.clsoft.bave.dao.rowmapper.common.StringRowMapper;
import cl.clsoft.bave.exception.DaoException;

public class GenericDao<T> {

    private static final String TAG = "GenericDao";

    public void insert(String table, ContentValues values) throws DaoException {
        Log.d(TAG, "insert");

        try {
            SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();
            db.insert(table, null, values);
        } catch(SQLiteException e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        }
    }

    public void update(String table, ContentValues values, String whereSql, Object... parameters) throws DaoException {
        Log.d(TAG, "update");

        try {
            SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();
            if (parameters != null) {
                String[] args = new String[parameters.length];
                int index = 0;
                for (Object parameter : parameters) {
                    args[index] = parameter.toString();
                    index++;
                }
                db.update(table, values, whereSql, args);
            } else {
                db.update(table, values, null, null);
            }
        } catch(SQLiteException e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        }
    }

    public void delete(String table, String whereSql, Object... parameters) throws DaoException {
        Log.d(TAG, "delete");

        try {
            SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();
            if (parameters != null) {
                String[] args = new String[parameters.length];
                int index = 0;
                for (Object parameter : parameters) {
                    args[index] = parameter.toString();
                    index++;
                }
                db.delete(table, whereSql, args);
            } else {
                db.delete(table, null, null);
            }
        } catch(SQLiteException e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        }
    }

    public T selectOne(String sql, RowMapper<T> rm, Object... parameters) throws DaoException {
        Log.d(TAG, "selectOne");

        try {
            T output = null;
            Cursor cursor = this.getCursor(sql, parameters);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        output = rm.mapRow(cursor, cursor.getPosition());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
            return output;
        } catch(SQLiteException e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        }
    }

    public List<T> selectMany(String sql, RowMapper<T> rm, Object... parameters) throws DaoException {
        Log.d(TAG, "selectMany");

        try {
            List<T> output = new ArrayList<>();
            Cursor cursor = this.getCursor(sql, parameters);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            T entity = rm.mapRow(cursor, cursor.getPosition());
                            output.add(entity);
                        } while(cursor.moveToNext());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
            return output;
        } catch(SQLiteException e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        }
    }

    public List<String> selectManyString(String sql, Object... parameters) throws DaoException {
        Log.d(TAG, "selectManyString");

        try {
            List<String> output = new ArrayList<>();
            StringRowMapper rm = new StringRowMapper();
            Cursor cursor = this.getCursor(sql, parameters);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            String entity = rm.mapRow(cursor, cursor.getPosition());
                            output.add(entity);
                        } while(cursor.moveToNext());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
            Log.d(TAG, "selectManyString::output size: " + output.size());
            return output;
        } catch(SQLiteException e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        }
    }

    public List<Long> selectManyLong(String sql, Object... parameters) throws DaoException {
        Log.d(TAG, "selectManyLong");

        try {
            List<Long> output = new ArrayList<>();
            LongRowMapper rm = new LongRowMapper();
            Cursor cursor = this.getCursor(sql, parameters);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            Long entity = rm.mapRow(cursor, cursor.getPosition());
                            output.add(entity);
                        } while(cursor.moveToNext());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
            return output;
        } catch(SQLiteException e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        }
    }

    public List<Double> selectManyDouble(String sql, Object... parameters) throws DaoException {
        Log.d(TAG, "selectManyDouble");

        try {
            List<Double> output = new ArrayList<>();
            DoubleRowMapper rm = new DoubleRowMapper();
            Cursor cursor = this.getCursor(sql, parameters);
            if (cursor != null) {

                try {
                    if (cursor.moveToFirst()) {
                        do {
                            Double entity = rm.mapRow(cursor, cursor.getPosition());
                            output.add(entity);
                        } while(cursor.moveToNext());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
            return output;
        } catch(SQLiteException e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new DaoException(2, e.getMessage());
        }
    }

    private Cursor getCursor(String sql, Object... parameters) {
        SQLiteDatabase db = DataBaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = null;
        List<String> output = new ArrayList<>();
        if (parameters != null) {
            String[] args = new String[parameters.length];
            int index = 0;
            for (Object parameter : parameters) {
                if (parameter != null) {
                    args[index] = parameter.toString();
                } else {
                    args[index] = "";
                }
                index++;
            }
            cursor = db.rawQuery(sql, args);
        } else {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
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
