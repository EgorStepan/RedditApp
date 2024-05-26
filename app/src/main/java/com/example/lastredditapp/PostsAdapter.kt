package com.example.lastredditapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter(var posts: List<Posts>, var context: Context,  var clickListener: OnImageClickListener) :RecyclerView.Adapter<PostsAdapter.MyViewHolder>(){

    //інтерфейс для створення івенту натискання на картинку
    interface OnImageClickListener {
        fun onImageClick(posts: Posts)
    }
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val author: TextView = view.findViewById(R.id.author_posts)
        val timepass: TextView = view.findViewById(R.id.time_pas_posts)
        val title: TextView = view.findViewById(R.id.title_post_text)
        val urltext: TextView = view.findViewById(R.id.link_post_text)
        val comment: TextView = view.findViewById(R.id.comments_count_text)
        val image: ImageView = view.findViewById(R.id.image_posts)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_in_list, parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return posts.count()

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.author.text = posts[position].author

        //ми отримуємо данні про час у секундах в форматі строки, а нам потрібно їх відобразити у ворматі X hours ago
        var timePass = posts[position].time_pas.toInt()
        timePass = timePass /60 /60
        holder.timepass.text = "$timePass hours ago"

        holder.title.text = posts[position].title
        holder.urltext.text = posts[position].link
        holder.comment.text = posts[position].count_comm

        //знаходження картинки для цього поста
        val imageId = context.resources.getIdentifier(
            posts[position].image,
            "drawable",
            context.packageName
        )
        holder.image.setImageResource(imageId)

        //виклик функції при натисканні на картинку
        holder.image.setOnClickListener{
            clickListener.onImageClick(posts[position])
        }

    }
}