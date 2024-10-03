package com.example.readyplay.features.paging

interface Pagination<Key, Item> {
    suspend fun loadNextPage()
    fun reset()
}