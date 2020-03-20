package com.dinesh.core.common.mapper

interface TwoWayMapper<FROM, TO> : Mapper<FROM, TO> {

    fun mapFrom(to: TO): FROM
}