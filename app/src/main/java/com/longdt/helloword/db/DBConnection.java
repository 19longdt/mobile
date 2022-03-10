package com.longdt.helloword.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.longdt.helloword.dao.CategoryDAO;
import com.longdt.helloword.entity.Category;
import com.longdt.helloword.entity.Product;

@Database(entities = {Category.class}, version = 1)
public abstract class DBConnection extends RoomDatabase {
    public abstract CategoryDAO createCateDAO();

}
