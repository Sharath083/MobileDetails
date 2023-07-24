package com.example.logic

import com.example.dao.DatabaseFactory
import com.example.dao.PropertiesDao
import com.example.data.request.Properties
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement


class DaoQueryImpl : DaoQuery {
    override suspend fun insertIntoTable(input: List<Properties>): Unit = DatabaseFactory.dbQuery {
        val insertStatement=input.map { inList->
            PropertiesDao.insert {
                it[id]=inList.id
                it[title]=inList.title
                it[body]=inList.description
                it[price]=inList.price
                it[discountPercentage]=inList.discountPercentage
                it[rating]=inList.rating
                it[stock]=inList.stock
                it[brand]=inList.brand
                it[category]=inList.category
                it[thumbnail]=inList.thumbnail
                it[images]= Json.encodeToString(inList.images)
            }
        }
        insertStatement.map { it ->
            it.resultedValues?.singleOrNull().let {
            if (it != null) {
                resultRowToFlight(it)
            }
        } }


    }

    override suspend fun insertIntoTableProd(input: Products) {
        val insertStatement=input.products.map { inList->
            PropertiesDao.insert {
                it[id]=inList.id
                it[title]=inList.title
                it[body]=inList.description
                it[price]=inList.price
                it[discountPercentage]=inList.discountPercentage
                it[rating]=inList.rating
                it[stock]=inList.stock
                it[brand]=inList.brand
                it[category]=inList.category
                it[thumbnail]=inList.thumbnail
                it[images]= Json.encodeToString(inList.images)
            }
        }
        insertStatement.map { it ->
            it.resultedValues?.singleOrNull().let {
                if (it != null) {
                    resultRowToFlight(it)
                }
            } }
    }


    private fun resultRowToFlight(row: ResultRow): Properties {
        return  Properties(row[PropertiesDao.id], row[PropertiesDao.title], row[PropertiesDao.body], row[PropertiesDao.price],
            row[PropertiesDao.discountPercentage],row[PropertiesDao.rating],row[PropertiesDao.stock],
            row[PropertiesDao.brand],row[PropertiesDao.category],row[PropertiesDao.thumbnail],row[PropertiesDao.images].split(","))

    }

}


//    override suspend fun insertIntoTable(input: List<Properties>) {
//    for (product in input) {
//        PropertiesDao.insert {
//            it[id] = product.id
//            it[title] = product.title
//            it[body] = product.description
//            it[price] = product.price
//            it[discountPercentage] = product.discountPercentage
//            it[rating] = product.rating
//            it[stock] = product.stock
//            it[brand] = product.brand
//            it[category] = product.category
//            it[thumbnail] = product.thumbnail
//            it[images] = Json.encodeToString(product.images) // Convert images to a JSON string
//        }
//    }
//}