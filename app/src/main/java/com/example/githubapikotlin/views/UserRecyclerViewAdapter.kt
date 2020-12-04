package com.example.githubapikotlin.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.example.githubapikotlin.R
import com.example.githubapikotlin.user.User
import com.squareup.picasso.Picasso
import com.example.githubapikotlin.lib.transform.CircleTransform;

class UserRecyclerViewAdapter(private val listUsers: List<User>) :
    RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val textView: LinearLayout) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_text_view, parent, false) as LinearLayout

        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var textViewLogin = holder.textView.findViewById<TextView>(R.id.user_text_view_login);
        textViewLogin.text = listUsers[position].login

        var textViewNode = holder.textView.findViewById<TextView>(R.id.user_text_view_node);
        textViewNode.text = listUsers[position].nodeId

        var imageView = holder.textView.findViewById<ImageView>(R.id.user_text_view_avatar);

        Picasso.get()
            .load(listUsers[position].avatarUrl)
            .resize(50, 50)
            .placeholder(AvatarGenerator.avatarImage(imageView.getContext(), 200, AvatarConstants.CIRCLE, "Android"))
            .transform(CircleTransform())
            .into(imageView)
    }

    override fun getItemCount() = listUsers.size
}
