package org.example

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.example.data.User
import org.example.data.UserDao

fun main(args: Array<String>) {
    val userDao = UserDao()
    val javalinApp = Javalin.create().start(7000)


    javalinApp.routes {

        /*
        *
        * READ
        *
        */

        get("/") { it ->
            it.json(userDao.users)
        }
        get("/users") { it ->
            it.json(userDao.users)
        }

        get("/get_user_by_id/:user-id") { it ->
            it.json(
                userDao.findById(it.pathParam("user-id").toInt()) ?: User(
                    -1,
                    "No User Found with such Id found",
                    "No Email"
                )
            )
        }
        get("/get_user_by_email/:user-email") { it ->
            it.json(
                userDao.findByEmail(it.pathParam("user-email")) ?: User(
                    -1,
                    "No User Found with such Email",
                    "No Email"
                )
            )
        }
        get("/get_user_by_name/:user-name") { it ->
            it.json(
                userDao.findByName(it.pathParam("user-name")) ?: User(
                    -1,
                    "No User Found with such Name",
                    "No Email"
                )
            )
        }

        /*
        *
        * CREATE
        *
        */

        post("/users") { it ->
            val user = it.body<User>()
            userDao.saveNewUser(name = user.name, email = user.email)
            it.status(201)
        }

        /*
        *
        * Update
        *
        */

        patch("/users/:user-id") { it ->
            val user = it.body<User>()
            userDao.updateUser(
                id = it.pathParam("user-id").toInt(),
                user = user
            )
            it.status(204)
        }

        /*
        *
        * DELETE
        *
        *
        */

        delete("/users/:user-id") { it ->
            userDao.deleteUser(it.pathParam("user-id").toInt())
            it.status(204)
        }
    }
}