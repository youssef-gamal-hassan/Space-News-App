package com.example.spacenewsapp

import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator.Position
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ArticleAdapter(
    var ArticleTitles: ArrayList<String>,
    var ArticleImages: ArrayList<String>,
    var ArticleUrl: ArrayList<String>,
    var ArticleSummary: ArrayList<String>,
    var context: Context
) :RecyclerView.Adapter<ArticleAdapter.ArticleAdapterHolder>() {
    class ArticleAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var articleTitle: TextView = itemView.findViewById(R.id.cdTitleTV)
        var articleSummary: TextView = itemView.findViewById(R.id.cdSummaryTV)
        var articleImage: ImageView = itemView.findViewById(R.id.cdImageView)
        var cardView: CardView = itemView.findViewById(R.id.CardDesign)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapterHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return ArticleAdapterHolder(view)
    }

    override fun getItemCount(): Int {
        return ArticleTitles.size
    }

    override fun onBindViewHolder(holder: ArticleAdapterHolder, position: Int) {
        holder.articleTitle.text = ArticleTitles[position]
        holder.articleSummary.text = ArticleSummary[position]
        Picasso.get()
            .load(ArticleImages[position])
            .resize(180, 150)
            .centerInside()
            .placeholder(R.drawable.spaceimage)
            .into(holder.articleImage)
        holder.cardView.setOnClickListener {
            val webIntent: Intent = Uri.parse(ArticleUrl[position]).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(context, webIntent, null)
        }
    }
}