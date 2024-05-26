package com.example.lastredditapp

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

class MainActivity : AppCompatActivity(), PostsAdapter.OnImageClickListener {
    //змінна поточної сторінки користувача
    private var currentPage = 0

    //змінна максимальної кількості постів на одній сторінки
    private val pageSize = 5

    //змінна поточної відкритої картинки користувача
    private var openImageNow:String = ""

    //Створення діалогу для відображення картинок у збільшеному форматі
    fun createSimpleDialog(image: ImageView) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_image, null)
        var imageView = dialogLayout.findViewById<ImageView>(R.id.dialogImage)

        imageView.setImageDrawable(image.drawable)
        builder.setView(dialogLayout)

        builder.setPositiveButton("OK") { dialog, which ->
            openImageNow=""
        }
        builder.setNeutralButton("Cancel") { dialog, which ->
           openImageNow=""
        }
        builder.show()
    }



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //збереження змінної поточної сторінки користувача з попереднього закриття
        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt("currentPage", 0)
            openImageNow = savedInstanceState.getString("openImageNow", "")
        }

        // ініціалізація масиву з постами
        val postsList: RecyclerView = findViewById(R.id.posts_list)
        val posts = arrayListOf<Posts>()

        //додавання кнопок Вперед та Назад
        val btnNextPage: Button = findViewById(R.id.button_forward_main)
        val btnPreviousPage: Button = findViewById(R.id.button_back_main)

        //функція яка контролює відображення постів
        fun loadPosts() {
            val startIndex = currentPage * pageSize
            val endIndex = min(startIndex + pageSize, posts.size)
            val subList = posts.subList(startIndex, endIndex)
            postsList.layoutManager = LinearLayoutManager(this)
            postsList.adapter = PostsAdapter(subList, this, this)
        }

        //додавання постів в масив
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

        //виклик функції про відображення стану додатку до закриття
        loadPosts()
        if(openImageNow != ""){
            val imageView = ImageView(this)
            val resourceId = resources.getIdentifier(openImageNow, "drawable", packageName)
            imageView.setImageResource(resourceId)
            createSimpleDialog(imageView)

        }

        //перехід на попередню сторінку
        btnPreviousPage.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                loadPosts()
            }
        }
        //перехід на наступну сторінку
        btnNextPage.setOnClickListener {
            if ((currentPage + 1) * pageSize < posts.size) {
                currentPage++
                loadPosts()
            }
        }




    }

    //збереження змінної про поточну сторінку користувача
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentPage", currentPage)
        outState.putString("openImageNow", openImageNow)
    }

    //перевизначення функції про клік на картинку для її відображення у діалогу
    override fun onImageClick(posts: Posts) {
        //збереження картинки якщо додаток зактиють
        openImageNow = posts.image

        //конвертація картинки у тип ImageView
        val imageView = ImageView(this)
        val resourceId = resources.getIdentifier(posts.image, "drawable", packageName)
        imageView.setImageResource(resourceId)

        //відправляння її в функцію діалогу
        createSimpleDialog(imageView)
    }
}