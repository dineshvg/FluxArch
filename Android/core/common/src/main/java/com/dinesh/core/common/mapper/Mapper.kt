package com.dinesh.core.common.mapper

interface Mapper<in FROM, out TO> {
    fun mapTo(from: FROM): TO
}