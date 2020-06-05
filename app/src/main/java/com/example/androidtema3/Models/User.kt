package com.example.androidtema3.Models

class User(id: Int,name: String, email: String) {
    var id: Int? = null
    var name: String? = null
    var email: String? = null

    init{
        this.id=id
        this.name=name
        this.email=email
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User
        if (name != other.name) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return "User $id $name $email"
    }
}