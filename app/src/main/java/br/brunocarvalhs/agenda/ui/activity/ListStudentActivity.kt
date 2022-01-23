package br.brunocarvalhs.agenda.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import br.brunocarvalhs.agenda.R
import br.brunocarvalhs.agenda.dao.StudentDAO
import br.brunocarvalhs.agenda.model.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

class ListStudentActivity : AppCompatActivity() {

    private val listView: ListView by lazy {
        findViewById(R.id.activity_list_student_list_view) }

    private val buttonAdd: FloatingActionButton by lazy {
        findViewById(R.id.activity_list_student_fab_new_student) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)
        this.toolbar()
        this.setupButton()
    }

    override fun onResume() {
        super.onResume()
        this.listRender()
    }

    private fun toolbar() {
        title = getString(R.string.activity_list_student_name)
    }

    private fun listRender(){
        val dao: StudentDAO = StudentDAO()
        listView.adapter =
            ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, dao.all())
        this.showStudent(dao.all())
    }

    private fun setupButton() {
        buttonAdd.setOnClickListener {
            this.redirect()
        }
    }

    private fun redirect(objects: Serializable? = null) {
        val intent = Intent(this, FormStudentActivity::class.java)
        objects?.let { intent.putExtra("student", it) }
        startActivity(intent)
    }

    private fun showStudent(dao: ArrayList<Student>) {
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                var student = dao[position]
                Log.i("Posição do aluno", "showStudent: ${student.name}")
                this.redirect(student)
            }
    }
}