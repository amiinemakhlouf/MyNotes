package com.example.newapp.di

import android.content.Context
import androidx.room.Room
import com.example.newapp.data.NoteDatabase
import com.example.newapp.data.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): NoteDatabase {

        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideExpenseDao(NoteDatabase: NoteDatabase): NoteDao {
        return NoteDatabase.noteDAO
    }


}