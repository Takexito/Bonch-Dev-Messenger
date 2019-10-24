package bonch.dev.school.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bonch.dev.school.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        sign_in_button.setOnClickListener {
            startActivity(Intent(this, MainAppActivity::class.java))
        }

        sign_up_button.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
