package com.anangkur.beritaku.feature.home

import android.view.View
import com.anangkur.beritaku.R
import com.anangkur.beritaku.base.BaseAdapter
import com.anangkur.beritaku.data.model.news.Article
import com.anangkur.beritaku.util.setImageUrl
import kotlinx.android.synthetic.main.item_regular.view.*

class RegularAdapter: BaseAdapter<Article>() {
    override val layout: Int
        get() = R.layout.item_regular

    override fun bind(data: Article, itemView: View, position: Int) {
        itemView.iv_item_regular.setImageUrl(data.urlToImage)
        itemView.tv_item_regular.text = data.title
    }
}