package bonch.dev.school.ui.activities

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bonch.dev.school.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth = FirebaseAuth.getInstance()


        complete_sign_up_button.setOnClickListener {
            if (email_sign_up_et.text.toString().length >= 8 &&
                password_sign_up_et.text.toString().length >= 5 &&
                login_et.text.toString().length >= 3) {

                if (password_sign_up_et.text.toString() == password_confirm_et.text.toString())
                    signUp(
                        email_sign_up_et.text.toString(),
                        password_sign_up_et.text.toString(),
                        login_et.text.toString()
                    ) //TODO: Add more limits
                else Toast.makeText(
                    baseContext, "Passwords do not match! ",
                    Toast.LENGTH_SHORT
                ).show()

            }
            else Toast.makeText(
                baseContext, "Password length must be > 4 & name > 2",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun signUp(email: String, password: String, name: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("BonchMess", "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())
                    //updateUI(user)
                    startActivity(Intent(this, MainAppActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("BonchMess", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
