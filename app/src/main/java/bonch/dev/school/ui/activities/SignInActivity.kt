package bonch.dev.school.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bonch.dev.school.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        if (currentUser != null) startChat()

        sign_in_button.setOnClickListener {
            if(email_sign_in_edit_text.text.toString().length >= 8 && password_sign_in_edit_text.text.toString().length >= 5)
            signIn(
                email_sign_in_edit_text.text.toString(),
                password_sign_in_edit_text.text.toString()
            )  //TODO: Add more limits

            else{
                Toast.makeText(
                    baseContext, "Password length must be >= 5",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        sign_up_button.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("BonchMess", "signInWithEmail:success")
                    val user = mAuth.currentUser
                    //updateUI(user)
                    startChat()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("BonchMess", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun startChat() {
        startActivity(Intent(this, MainAppActivity::class.java))
    }

}
