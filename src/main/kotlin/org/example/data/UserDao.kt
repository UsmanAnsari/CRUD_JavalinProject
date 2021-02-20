package org.example.data

import java.util.concurrent.atomic.AtomicInteger

class UserDao {

    val users = hashMapOf(
        0 to User(0, "Usman Ali Ansari", "usman_ansari@gmail.com"),
        1 to User(1, "Ali Ahmed", "ali_ahmed@gmail.com"),
        2 to User(2, "Ali Hamza", "ali_hamza@gmail.com"),
        3 to User(3, "Asad Nisar", "asad_nisar@gmail.com"),
        4 to User(4, "Hadi", "hadi@gmail.com"),
        5 to User(5, "Hassan", "hassan@gmail.com")
    )

    var lastId: AtomicInteger = AtomicInteger(users.size - 1)

    /*
    *
    * CREATE
    *
    */

    fun saveNewUser(name: String, email: String) {
        val id = lastId.incrementAndGet()
        users.put(id, User(id = id, name = name, email = email))
    }

    /*
    *
    * READ
    *
    */

    fun findById(id: Int): User? {
        return users[id]
    }

    fun findByEmail(email: String): User? {
        return users.values.find {
            it.email == email
        }
    }

    fun findByName(name: String): User? {
        return users.values.find {
            it.name == name
        }
    }

    /*
    *
    * Update
    *
    */

    fun updateUser(id: Int, user: User) {
        users.put(id, User(id = id, name = user.name, email = user.email))
    }

    /*
    *
    * DELETE
    *
    *
    */
    fun deleteUser(id: Int) {
        users.remove(id)
    }
}