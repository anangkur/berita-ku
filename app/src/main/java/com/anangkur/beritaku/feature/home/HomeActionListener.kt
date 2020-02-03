package com.anangkur.beritaku.feature.home

import com.anangkur.beritaku.data.model.news.Article

interface HomeActionListener {
    fun onClickItem(data: Article)
}