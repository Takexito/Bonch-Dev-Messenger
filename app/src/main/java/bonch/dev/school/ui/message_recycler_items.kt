package bonch.dev.school.ui

import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.R
import bonch.dev.school.models.Message

class MainAdapter(var items: List<Message>, val callback: Callback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        1 -> OtherHolder(from(parent.context).inflate(R.layout.other_message_item, parent, false))
        else -> UserHolder(from(parent.context).inflate(R.layout.user_message_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> (holder as OtherHolder).bind(items[position])
            else -> (holder as UserHolder).bind(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isUser) 0 else 1
    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val message = itemView.findViewById<TextView>(R.id.user_message_view)
        private val date = itemView.findViewById<TextView>(R.id.user_date_view)

        fun bind(item: Message) {
            message.text = item.messageText
            date.text = item.sentDate.toString()
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    inner class OtherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val message = itemView.findViewById<TextView>(R.id.other_message_view)
        private val date = itemView.findViewById<TextView>(R.id.other_date_view)
        private val name = itemView.findViewById<TextView>(R.id.other_name_view)

        fun bind(item: Message) {
            message.text = item.messageText
            date.text = item.sentDate.toString()
            name.text = item.name
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: Message)
    }

}