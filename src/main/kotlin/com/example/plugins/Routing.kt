package com.example.plugins

import com.example.data.request.Properties
import com.example.logic.DaoQueryImpl
import com.example.logic.Methods
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import io.ktor.server.application.*
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {

        get("/insertData") {
//            val url=call.parameters["url"] ?:return@get call.respondText(
//                "Missing url",
//                status = HttpStatusCode.BadRequest
//            )
            val url="https://dummyjson.com/products"
            val method= Methods()
            val list=method.propertiesList(url)
//            call.respond(list!!)
//            val data=method.parseProductsFromJson(list)
            val dao=DaoQueryImpl()
            dao.insertIntoTableProd(list!!)
            call.respond(" Inserted")

        }
    }
}
