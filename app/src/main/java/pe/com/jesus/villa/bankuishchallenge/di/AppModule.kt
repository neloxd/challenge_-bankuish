package pe.com.jesus.villa.bankuishchallenge.di


import android.app.Application
import androidx.room.Room
import com.jesusvilla.data.repository.PermissionChecker
import com.jesusvilla.data.source.LocalDataSource
import com.jesusvilla.data.source.RemoteDataSource
import com.jesusvilla.data.source.handler.ResponseHandler
import pe.com.jesus.villa.bankuishchallenge.data.AndroidPermissionChecker
import pe.com.jesus.villa.bankuishchallenge.data.database.ItemDatabase
import pe.com.jesus.villa.bankuishchallenge.data.database.RoomDataSource
import pe.com.jesus.villa.bankuishchallenge.data.server.TheItemDbDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //without password
    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        ItemDatabase::class.java,
        "bankuish-db"
    ).allowMainThreadQueries().build()

    //with password
//    @Provides
//    @Singleton
//    fun databaseProvider(app: Application) = ItemDatabase.getInstance(app, "bankuish-db")

    @Provides
    fun localDataSourceProvider(db: ItemDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(app: Application): RemoteDataSource = TheItemDbDataSource(app)

    @Provides
    fun permissionCheckerProvider(app: Application): PermissionChecker =
        AndroidPermissionChecker(app)

    @Provides
    fun responseHandler(): ResponseHandler =
        ResponseHandler()
}