package br.brunocarvalhs.agenda.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import br.brunocarvalhs.agenda.R
import br.brunocarvalhs.agenda.dao.StudentDAO
import br.brunocarvalhs.agenda.model.Student
import java.util.regex.Matcher
import java.util.regex.Pattern

class FormStudentActivity : AppCompatActivity() {

    var dao = StudentDAO()

    private val nameInput: AppCompatEditText by lazy { findViewById(R.id.activity_form_student_name) }
    private val mailInput: AppCompatEditText by lazy { findViewById(R.id.activity_form_student_mail) }
    private val phoneInput: AppCompatEditText by lazy { findViewById(R.id.activity_form_student_phone) }
    private val buttonSave: AppCompatButton by lazy { findViewById(R.id.activity_form_student_button_save) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_student)
        this.toolbar()
        this.buttonEvent()
        this.regexSetup()
    }

    override fun onResume() {
        super.onResume()
        if(intent.hasExtra("student")) this.getStudent(intent)
    }


    private fun toolbar() {
        title = getString(R.string.activity_form_student_name)
    }

    private fun buttonEvent() {
        buttonSave.setOnClickListener {
            if(intent.hasExtra("student")) this.updateStudent()
            else this.createStudent()
            finish()
        }
    }

    private fun createStudent() {
        var name = nameInput.text.toString()
        var mail = mailInput.text.toString()
        var phone = phoneInput.text.toString()

        var student = Student(name, phone, mail)

        save(student)
    }

    private fun updateStudent() {
        val registerOfStudent: Student = intent.getSerializableExtra("student") as Student
        var name = nameInput.text.toString()
        var mail = mailInput.text.toString()
        var phone = phoneInput.text.toString()

        var student = Student(name, phone, mail)
        dao.put(student, registerOfStudent.id)
    }

    private fun save(student: Student) {
        dao.save(student)
    }

    private fun getStudent(intent: Intent) {
        val student: Student = intent.getSerializableExtra("student") as Student
        nameInput.setText(student.name as CharSequence)
        mailInput.setText(student.mail as CharSequence)
        phoneInput.setText(student.phone as CharSequence)
    }

    private fun regexSetup() {
        phoneInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val editable: String = phoneInput.text.toString()
                val str: String? = this@FormStudentActivity.stringFilter(editable)
                if (editable != str) {
                    phoneInput.setText(str);
                    phoneInput.setSelection(phoneInput.text.toString().length);
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun stringFilter(str: String?): String? {
        // Only letters, numbers and English blank characters are allowed
        val regEx = """[^a-zA-Z0-9s]"""
        val p: Pattern = Pattern.compile(regEx)
        val m: Matcher = p.matcher(str)
        return m.replaceAll("")
    }
}