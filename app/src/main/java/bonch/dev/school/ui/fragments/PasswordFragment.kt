package bonch.dev.school.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import bonch.dev.school.R
import kotlinx.android.synthetic.main.fragment_password.*

class PasswordFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        acceptButton.setOnClickListener {

            dialog?.dismiss()
        }
        Log.d("PassFragment:", "Is show? = ${dialog?.isShowing}")
    }

    companion object {

        @JvmStatic
        fun newInstance() = PasswordFragment()
    }
}