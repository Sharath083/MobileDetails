package com.example.logic

import com.example.data.request.Properties

interface DaoQuery {

    suspend fun insertIntoTable(input:List<Properties>)
    suspend fun insertIntoTableProd(input:Products)

}