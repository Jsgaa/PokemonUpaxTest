package com.JuanSalvadorGarcia.upaxtest.data.di

import android.content.Context
import androidx.room.Room
import com.JuanSalvadorGarcia.upaxtest.data.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val POKEMON_DATABASE_NAME = "pokemon_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, PokemonDatabase::class.java, POKEMON_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePokemonDetailDao(db: PokemonDatabase) = db.getPokemonDetailDao()

}