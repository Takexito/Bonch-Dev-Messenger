package bonch.dev.school.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.time.LocalTime

data class Message(
    val messageId: Int,
    val messageText: String,
    val sentDate: LocalTime,
    val isUser: Boolean,
    val name: String
) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.O)
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(
        messageId: Int,
        messageText: String,
        sentDate: String,
        isUser: Boolean,
        name: String
    ) : this(
        messageId, messageText, LocalTime.parse(sentDate), isUser, name
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(messageId)
        parcel.writeString(messageText)
        parcel.writeString(sentDate.toString())
        parcel.writeByte(if (isUser) 1 else 0)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Message> {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun createFromParcel(parcel: Parcel): Message {
            return Message(parcel)
        }

        override fun newArray(size: Int): Array<Message?> {
            return arrayOfNulls(size)
        }
    }
}