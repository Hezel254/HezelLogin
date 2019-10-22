package com.example.hezellogin.model

class Task {
    var id:String? = null
    var name:String? = null
    var description:String? = null
    var date:String? = null

    constructor(id: String, name: String, description: String, date: String) {
        this.id = id
        this.name = name
        this.description = description
        this.date = date
    }

    constructor(name: String, description: String, date: String) {
        this.name = name
        this.description = description
        this.date = date
    }


}