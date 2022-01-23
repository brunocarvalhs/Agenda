package br.brunocarvalhs.agenda.dao

import br.brunocarvalhs.agenda.model.Student
import java.util.*
import kotlin.collections.ArrayList

class StudentDAO {

    companion object {
        var listStudent: MutableList<Student> = mutableListOf()
    }

    fun save(student: Student) = listStudent.add(student)

    fun all() = ArrayList<Student>(listStudent)

    fun put(data: Student, uuid: UUID) {
        listStudent.forEach { student ->
            if(student.id == uuid) {
                student.name = data.name
                student.phone = data.phone
                student.mail = data.mail
            }
        }

    }

}
