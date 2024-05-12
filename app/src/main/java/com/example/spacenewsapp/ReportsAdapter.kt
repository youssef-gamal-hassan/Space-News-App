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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ReportsAdapter (
    var ReportsTitles: ArrayList<String>,
    var ReportsImages: ArrayList<String>,
    var ReportsUrl: ArrayList<String>,
    var ReportsSummary: ArrayList<String>,
    var context: Context
    ) :RecyclerView.Adapter<ReportsAdapter.ReportsAdapterHolder>() {
        class ReportsAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var articleTitle: TextView = itemView.findViewById(R.id.cdTitleTV)
            var articleSummary: TextView = itemView.findViewById(R.id.cdSummaryTV)
            var articleImage: ImageView = itemView.findViewById(R.id.cdImageView)
            var cardView: CardView = itemView.findViewById(R.id.CardDesign)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsAdapterHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
            return ReportsAdapterHolder(view)
        }

        override fun getItemCount(): Int {
            return ReportsTitles.size
        }

        override fun onBindViewHolder(holder: ReportsAdapterHolder, position: Int) {
            holder.articleTitle.text = ReportsTitles[position]
            holder.articleSummary.text = ReportsSummary[position]
            Picasso.get()
                .load(ReportsImages[position])
                .resize(180, 150)
                .centerInside()
                .placeholder(R.drawable.spaceimage)
                .into(holder.articleImage)
            holder.cardView.setOnClickListener {
                val webIntent: Intent = Uri.parse(ReportsUrl[position]).let { webpage ->
                    Intent(Intent.ACTION_VIEW, webpage)
                }
                startActivity(context, webIntent, null)
            }
        }
}