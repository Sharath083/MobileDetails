package com.example.dao


import org.jetbrains.exposed.sql.Table


object PropertiesDao : Table("details_table") {



//    id SERIAL PRIMARY KEY,
//    title TEXT NOT NULL,
//    description TEXT NOT NULL,
//    price NUMERIC NOT NULL,
//    discountPercentage NUMERIC NOT NULL,
//    rating NUMERIC NOT NULL,
//    stock INTEGER NOT NULL,
//    brand TEXT NOT NULL,
//    category TEXT NOT NULL,
//    thumbnail TEXT NOT NULL,
//    images TEXT[] NOT NULL
    

    val id = integer("id")
    val title = varchar("title", 128)
    val body = varchar("body", 1024)
    val price=integer("price")
    val discountPercentage=double("discountPercentage")
    val rating=double("rating")
    val stock=integer("stock")
    val brand=varchar("brand",45)
    val category=varchar("category",45)
    var thumbnail=varchar("thumbnail",100)
    var images= text("images")
    override val primaryKey = PrimaryKey(id)

}