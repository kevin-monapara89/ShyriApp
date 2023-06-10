package com.kevin.shyriapp

class ShyriModel{

    var id = 0
    var cat_id = 0
    lateinit var sms: String

    constructor(id: Int, cat_id: Int,sms: String) {
        this.id = id
        this.cat_id = cat_id
        this.sms = sms
    }
}