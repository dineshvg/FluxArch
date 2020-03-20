package com.dinesh.core.common.usecase

interface UseCase<out R> {

    suspend fun execute(): R
}