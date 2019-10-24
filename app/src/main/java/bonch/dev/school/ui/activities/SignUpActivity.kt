package bonch.dev.school.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bonch.dev.school.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        complete_sign_up_button.setOnClickListener {
            startActivity(Intent(this, MainAppActivity::class.java))
        }

    }
}
