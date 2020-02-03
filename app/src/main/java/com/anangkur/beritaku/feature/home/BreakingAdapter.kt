package com.anangkur.beritaku.feature.home

import android.view.View
import com.anangkur.beritaku.R
import com.anangkur.beritaku.base.BaseAdapter
import com.anangkur.beritaku.data.model.news.Article
import com.anangkur.beritaku.util.setImageUrl
import kotlinx.android.synthetic.main.item_breaking.view.*

class BreakingAdapter: BaseAdapter<Article>(){
    override val layout: Int
        get() = R.layout.item_breaking

    override fun bind(data: Article, itemView: View, position: Int) {
        itemView.iv_item_breaking.setImageUrl(data.urlToImage)
        itemView.tv_item_breaking.text = data.title
    }

}