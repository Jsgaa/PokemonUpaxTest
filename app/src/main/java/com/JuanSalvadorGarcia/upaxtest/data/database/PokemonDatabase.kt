package com.JuanSalvadorGarcia.upaxtest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.JuanSalvadorGarcia.upaxtest.data.database.dao.PokemonDetailDao
import com.JuanSalvadorGarcia.upaxtest.data.database.entities.PokemonDetailEntity

@Database(entities = [PokemonDetailEntity::class], version = 1)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun getPokemonDetailDao(): PokemonDetailDao
}