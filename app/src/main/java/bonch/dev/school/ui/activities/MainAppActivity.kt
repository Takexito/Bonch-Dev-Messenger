package bonch.dev.school.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import bonch.dev.school.R
import bonch.dev.school.ui.fragments.ChatFragment
import bonch.dev.school.ui.fragments.PasswordFragment
import bonch.dev.school.ui.fragments.ProfileFragment


class MainAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, ChatFragment.newInstance())
            .commit()

    }

    fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, ProfileFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    fun showDialog() {

        val ft = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) ft.remove(prev)

        ft.addToBackStack(null)

        // Create and show the dialog.
        val newFragment = PasswordFragment.newInstance()
        newFragment.show(ft, "dialog")
        Log.d("PassFragment:", "Dialog was show")
    }
}
