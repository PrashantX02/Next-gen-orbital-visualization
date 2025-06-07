package com.pain.space

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import com.pain.space.sheet_fragment.comment_sheetFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.functions.FirebaseFunctions
import com.pain.space.model.swipe_card_model
import com.pain.space.recyclerViews_and_adapter.CardStackAdapter
import com.pain.space.services.ViewCountService
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import resources_of_main_data.event_area_51
import resources_of_main_data.event_black
import resources_of_main_data.event_planetX
import resources_of_main_data.event_roswell

class swipe_card : AppCompatActivity() {private lateinit var manager: CardStackLayoutManager
    private lateinit var cardStackAdapter: CardStackAdapter
    private lateinit var cardStackView: CardStackView
    private lateinit var watching : TextView

    private val functions = FirebaseFunctions.getInstance()

    private  val  db = FirebaseDatabase.getInstance()
    private lateinit var key_default_watch : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_swipe_card)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cardStackView = findViewById(R.id.cardStackView)
        watching = findViewById(R.id.textView28)
        var com : ImageView = findViewById(R.id.comments)



        FirebaseApp.initializeApp(this)

        val key = intent.getStringExtra("ev")

        key_default_watch  = when (key) {
            "1" -> "default_card_1"
            "2" -> "default_card_2"
            "3" -> "default_card_3"
            "4" -> "default_card_4"
            else -> ""
        }

        Log.d("planet", "watching: "+key_default_watch)

        val db = FirebaseDatabase.getInstance()


        increaseViewCount()

        manager = CardStackLayoutManager(this, object : CardStackListener {
            override fun onCardDragging(direction: Direction, ratio: Float) {
                if (manager.topPosition == cardStackAdapter.itemCount) {
                }
            }

            override fun onCardSwiped(direction: Direction) {
                val position = manager.topPosition - 1
                val newList = cardStackAdapter.list.toMutableList()

                if (position >= 0 && position < newList.size) {
                    val swipedItem = newList[position]
                    newList.removeAt(position)
                    newList.add(swipedItem)
                    cardStackAdapter.setItems(newList)
                    manager.setTopPosition(position)
                } else {
                    manager.setTopPosition(0)
                }
            }

            override fun onCardRewound() {}
            override fun onCardCanceled() {}
            override fun onCardAppeared(view: View, position: Int) {}
            override fun onCardDisappeared(view: View, position: Int) {}

        })

        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(0.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.FREEDOM)
        manager.setCanScrollHorizontal(true)
        manager.setSwipeableMethod(SwipeableMethod.Manual)
        manager.setOverlayInterpolator(LinearInterpolator())

        cardStackAdapter = CardStackAdapter(getList(),this)

        cardStackView.layoutManager = manager
        cardStackView.adapter = cardStackAdapter
        cardStackView.itemAnimator = DefaultItemAnimator()


        // Comment handling

        com.setOnClickListener {
            val comment_sheetFragment = comment_sheetFragment(key_default_watch)
            comment_sheetFragment.show(supportFragmentManager,comment_sheetFragment?.tag)
        }

    }

    private fun getList(): List<swipe_card_model> {
        val list = ArrayList<swipe_card_model>()
        var str = ""

        val key = intent.getStringExtra("ev")
        if(key.equals("1")){
            str = event_area_51.area_51
            list.add(swipe_card_model("Area-51", event_area_51.area_51_description,"",R.drawable.area51))


        } else if(key.equals("2")){
            str = event_planetX.planet_x
            list.add(swipe_card_model("Planet-x", event_planetX.planet_x_description,"",R.drawable.planet_x))
        }else if(key.equals("3")){
            str = event_roswell.roswell_incident
            list.add(swipe_card_model("Roswell-1947", event_roswell.roswell_incident_description,"",R.drawable.roswell))
        }else {
            str = event_black.knight_black
            list.add(swipe_card_model("knight black", event_black.knight_black_description,"",R.drawable.black_knight))
        }

        while (str.length >= 1500){
            list.add(swipe_card_model("","",str.substring(0,1500),-1))
            str = str.substring(1500)
        }
        if(!str.isEmpty()) list.add(swipe_card_model("","",str,-1))

        return list
    }

    override fun onStop() {
        val serviceIntent = Intent(this, ViewCountService::class.java)
        serviceIntent.putExtra("key", key_default_watch)
        startService(serviceIntent)
        super.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    fun increaseViewCount(){
        db.reference
            .child(key_default_watch).child("live_views").get()
            .addOnSuccessListener { snapshot ->
                Log.d("planet", "enter")
                if (snapshot.exists()) {
                    var value : Int = snapshot.getValue(Int::class.java)  ?: 0
                    value++
                    Log.d("planet", "watching_val: $value")
                    watching.text = value.toString() + " watching"

                } else {
                    Log.d("planet", "No data found at $key_default_watch")
                    watching.text =  "1 watching"
                }

                db.reference.child(key_default_watch).child("live_views").setValue(1)
            }
            .addOnFailureListener { error ->
                Log.e("planet", "Firebase get failed: ${error.message}")
            }
    }


    override fun onResume() {
        super.onResume()
        increaseViewCount()
    }

}