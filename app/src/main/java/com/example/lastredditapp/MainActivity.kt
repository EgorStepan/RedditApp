package com.example.lastredditapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postsList: RecyclerView = findViewById(R.id.posts_list)
        val posts = arrayListOf<Posts>()

        posts.add(Posts(1,"affre","foto_1","Title post 1","Link_post_1","Many text for post 1", "1234", "32454"))
        posts.add(Posts(2,"fdssww","foto_2","Title post 2","Link_post_2","Many text for post 2", "354", "12454"))
        posts.add(Posts(3,"fadfdd","foto_3","Title post 3","Link_post_3","Many text for post 3", "654", "65454"))
        posts.add(Posts(4,"addfsadffasdf","foto_4","Title post 4","Link_post_4","Many text for post 4", "553", "54254"))
        posts.add(Posts(5,"sdadare","foto_5","Title post 5","Link_post_5","Many text for post 5", "56", "44254"))
        posts.add(Posts(6,"afadsfae","foto_6","Title post 6","Link_post_6","Many text for post 6", "54", "54254"))
        posts.add(Posts(7,"asdfsde","foto_7","Title post 7","Link_post_7","Many text for post 7", "34", "98254"))
        posts.add(Posts(8,"asdfsde","foto_8","Title post 8","Link_post_8","Many text for post 8", "12", "24254"))
        posts.add(Posts(9,"asfgfgre","foto_9","Title post 9","Link_post_9","Many text for post 9", "14", "32454"))
        posts.add(Posts(10,"asfgsfde","foto_10","Title post 10","Link_post_10","Many text for post 10", "784", "15654"))

        postsList.layoutManager = LinearLayoutManager(this)
        postsList.adapter = PostsAdapter(posts, this)


    }
}