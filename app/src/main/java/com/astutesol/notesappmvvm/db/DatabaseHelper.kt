package com.astutesol.notesappmvvm.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ItemToSell.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "sell_data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_QUANTITY = "quantity"
        private const val COLUMN_TYPE = "type"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_PRICE INTEGER, $COLUMN_QUANTITY INTEGER, $COLUMN_TYPE INTEGER)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertProduct(product: Product): Long {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, product.id)
        contentValues.put(COLUMN_NAME, product.name)
        contentValues.put(COLUMN_PRICE, product.price)
        contentValues.put(COLUMN_QUANTITY, product.quantity)
        contentValues.put(COLUMN_TYPE, product.type)

        val db = this.writableDatabase
        val id = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return id
    }

    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"

        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE))
                val quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY))
                val type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE))

                val product = Product(id, name, price, quantity, type)
                productList.add(product)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return productList
    }
}