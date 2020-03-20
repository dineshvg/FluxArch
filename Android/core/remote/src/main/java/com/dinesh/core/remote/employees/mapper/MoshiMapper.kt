package com.dinesh.core.remote.employees.mapper

import com.dinesh.core.remote.employees.model.RemoteEmployee
import com.squareup.moshi.JsonAdapter

import com.squareup.moshi.Moshi


class MoshiMapper {

    private val moshi = Moshi.Builder().build()
    val jsonAdapter: JsonAdapter<RemoteEmployee> =
        moshi.adapter(RemoteEmployee::class.java)
}