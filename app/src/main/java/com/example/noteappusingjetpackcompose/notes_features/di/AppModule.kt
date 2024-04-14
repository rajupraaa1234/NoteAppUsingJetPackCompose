package com.example.noteappusingjetpackcompose.notes_features.di
import android.app.Application
import androidx.room.Room
import com.example.noteappusingjetpackcompose.notes_features.data.data_source.NoteDao
import com.example.noteappusingjetpackcompose.notes_features.data.data_source.NoteDataBase
import com.example.noteappusingjetpackcompose.notes_features.data.repository.NoteRepositoryImpl
import com.example.noteappusingjetpackcompose.notes_features.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDataBase {
        return Room.databaseBuilder(
            app,
            NoteDataBase::class.java,
            name = NoteDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(implementation: NoteRepositoryImpl): NoteRepository {
        return implementation
    }

    @Provides
    fun provideNoteDao(database: NoteDataBase): NoteDao {
        return database.noteDao
    }
}
