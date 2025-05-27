package com.pain.space.recyclerViews_and_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pain.space.R
import com.pain.space.model.Comment

class CommentAdapter(private val commentList: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.textView31)
        val instant_comment_set : CardView = itemView.findViewById(R.id.cardView)
        val commentText: TextView = itemView.findViewById(R.id.textView30)
        val emotion : ImageView = itemView.findViewById(R.id.imageView34)
        val emotion_set : ConstraintLayout = itemView.findViewById(R.id.grid_emotion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_card, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = commentList[position]
        holder.username.text = comment.username

        holder.emotion.setOnClickListener {
            if(holder.emotion_set.isVisible){
                holder.instant_comment_set.visibility = View.VISIBLE
                holder.emotion_set.visibility = View.GONE
            }else{
                holder.instant_comment_set.visibility = View.GONE
                holder.emotion_set.visibility = View.VISIBLE
            }
        }
//        // Clear existing images before adding new ones
//        holder.emotionLayout.removeAllViews()
//
//        val emotions = listOf(
//            comment.chk,
//            comment.confused,
//            comment.cool,
//            comment.angry,
//            comment.facebookReactions,
//            comment.sad,
//            comment.sad2,
//            comment.neutral,
//            comment.science,
//            comment.einstein,
//            comment.sportCar,
//            comment.startup,
//            comment.chemistry,
//            comment.galaxy,
//            comment.angryBirds,
//            comment.tesla,
//            comment.gravity,
//            comment.isaacNewton,
//            comment.astronaut,
//            comment.planet
//        )

//        // Dynamically add emotion icons
//        emotions.forEach { drawableId ->
//            val imageView = ImageView(holder.itemView.context).apply {
//                setImageResource(drawableId)
//                val size = 64
//                layoutParams = LinearLayout.LayoutParams(size, size).apply {
//                    marginEnd = 16
//                }
//            }
//            holder.emotionLayout.addView(imageView)
//        }
    }

    override fun getItemCount(): Int = commentList.size
}
