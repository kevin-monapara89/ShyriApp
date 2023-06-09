package com.kevin.shyriapp

class ShyriModel{

    var id = 0
    lateinit var cat : String
    lateinit var shyri: String

    constructor(id: Int, cat: String, shyri: String) {
        this.id = id
        this.cat = cat
        this.shyri = shyri
    }
}