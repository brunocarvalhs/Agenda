package br.brunocarvalhs.agenda.model

import java.io.Serializable
import java.util.*

class Student : Serializable {

    val id: UUID
    var name: String
    var phone: String
    var mail: String

    constructor(name: String, phone: String, mail: String) {
        this.id = UUID.randomUUID()
        this.name = name
        this.phone = phone
        this.mail = mail
    }



    override fun toString(): String = name
}
