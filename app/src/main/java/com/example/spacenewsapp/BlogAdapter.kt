package com.example.spacenewsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BlogAdapter (
    var BlogTitles: ArrayList<String>,
    var BlogImages: ArrayList<String>,
    var BlogUrl: ArrayList<String>,
    var BlogSummary: ArrayList<String>,
    var context: Context
) : RecyclerView.Adapter<BlogAdapter.BlogAdapterHolder>() {
    class BlogAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var articleTitle: TextView = itemView.findViewById(R.id.cdTitleTV)
        var articleSummary: TextView = itemView.findViewById(R.id.cdSummaryTV)
        var articleImage: ImageView = itemView.findViewById(R.id.cdImageView)
        var cardView: CardView = itemView.findViewById(R.id.CardDesign)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogAdapterHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return BlogAdapterHolder(view)
    }

    override fun getItemCount(): Int {
        return BlogTitles.size
    }

    override fun onBindViewHolder(holder: BlogAdapterHolder, position: Int) {
        holder.articleTitle.text = BlogTitles[position]
        holder.articleSummary.text = BlogSummary[position]
        Picasso.get()
            .load(BlogImages[position])
            .resize(180, 150)
            .centerInside()
            .placeholder(R.drawable.spaceimage)
            .into(holder.articleImage)
        holder.cardView.setOnClickListener {
            val webIntent: Intent = Uri.parse(BlogUrl[position]).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            ContextCompat.startActivity(context, webIntent, null)
        }
    }
}