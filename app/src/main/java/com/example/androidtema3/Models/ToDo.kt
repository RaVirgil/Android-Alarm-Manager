package com.example.androidtema3.Models

class ToDo(title: String) {
    var title:String?=null


    init{
        this.title=title
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ToDo
        if (title != other.title) return false
        return true
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }

    override fun toString(): String {
        return "ToDo $title"
    }
}