package uz.gita.book_app_compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.book_app_compose.domain.repository.BookRepository
import uz.gita.book_app_compose.domain.repository.impl.BookRepositoryImpl
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/5/2022
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindRepository(impl: BookRepositoryImpl): BookRepository

}