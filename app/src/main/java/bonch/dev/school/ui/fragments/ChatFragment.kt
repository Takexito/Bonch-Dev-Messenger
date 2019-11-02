package bonch.dev.school.ui.fragments


import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import bonch.dev.school.R
import bonch.dev.school.models.Message
import bonch.dev.school.ui.MainAdapter
import kotlinx.android.synthetic.main.fragment_chat.*
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
class ChatFragment : Fragment() {

    private lateinit var myAdapter: MainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        items = mBundleRecyclerViewState?.getParcelableArrayList<Message>("CHATS") ?: items
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createRecyclerView()
        send_message_button.setOnClickListener {
            val str = message_text_input.text
            if (str == null || str[0] == '\n' || str[0] == ' ') return@setOnClickListener
            items.add(
                Message(
                    0,
                    message_text_input.text.toString(),
                    LocalTime.now(),
                    true,
                    "Takexito"
                )
            )
            message_text_input.text = Editable.Factory().newEditable("")
            myAdapter.notifyItemInserted(items.size - 1)
            message_recycler_view.scrollToPosition(items.size - 1)
        }
    }

    companion object {

        private var mBundleRecyclerViewState: Bundle? = null

        @JvmStatic
        fun newInstance() = ChatFragment()
    }

    private fun createRecyclerView() {
        myAdapter = MainAdapter(items, object : MainAdapter.Callback {
            override fun onItemClicked(item: Message) {
                //TODO on click
            }
        })

        message_recycler_view.adapter = myAdapter
        message_recycler_view.layoutManager =
            LinearLayoutManager(context).apply { stackFromEnd = true }
        message_recycler_view.scrollToPosition(items.size - 1)
    }

    override fun onPause() {
        super.onPause()

        mBundleRecyclerViewState = Bundle()
        mBundleRecyclerViewState?.putParcelableArrayList("CHATS", items)
    }

    override fun onResume() {
        super.onResume()

        if (mBundleRecyclerViewState != null) {
            items = mBundleRecyclerViewState?.getParcelableArrayList<Message>("CHATS")!!
            myAdapter.notifyItemInserted(items.size - 1)
            message_recycler_view.scrollToPosition(items.size - 1)
        }
    }

    var items = arrayListOf(
        Message(0, "Very big message to my friend", LocalTime.of(23, 0, 0), true, "Takexito"),
        Message(
            1,
            "Test Text? Very big message to my friend",
            LocalTime.of(1, 30, 0),
            false,
            "Other"
        ),
        Message(2, "Test Text", LocalTime.of(7, 0, 0), true, "Takexito"),
        Message(
            3,
            "Test TextVery big message to my friend",
            LocalTime.of(10, 0, 0),
            false,
            "Other"
        ),
        Message(4, "Test Text", LocalTime.of(12, 0, 0), true, "Takexito"),
        Message(
            0,
            "Test TextVery big message to my friend",
            LocalTime.of(15, 0, 0),
            true,
            "Takexito"
        ),
        Message(1, "Test Text", LocalTime.of(18, 0, 0), false, "Other"),
        Message(
            2,
            "Test TextVery big message to my friendTest TextVery big message to my friend Test TextVery big message to my friend",
            LocalTime.of(20, 0, 0),
            true,
            "Takexito"
        ),
        Message(
            3,
            "Test TextVery big message to my friendTest TextVery big message to my friend Test TextVery big message to my friend",
            LocalTime.of(21, 0, 0),
            false,
            "Other"
        ),
        Message(
            4,
            "Test TextVery big message to my friendTest TextVery big message to my friend Test TextVery big message to my friend",
            LocalTime.of(22, 40, 0),
            true,
            "Takexito"
        ),
        Message(
            1,
            "Test Text? Very big message to my friend",
            LocalTime.of(1, 30, 0),
            false,
            "Other"
        ),
        Message(2, "Test Text", LocalTime.of(7, 0, 0), true, "Takexito"),
        Message(
            3,
            "Test TextVery big message to my friend",
            LocalTime.of(10, 0, 0),
            false,
            "Other"
        )
    )

}
