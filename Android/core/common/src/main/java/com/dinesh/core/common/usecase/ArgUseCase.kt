package com.dinesh.core.common.usecase

interface ArgUseCase<in A, out R> {

    suspend fun execute(argument: A): R
}