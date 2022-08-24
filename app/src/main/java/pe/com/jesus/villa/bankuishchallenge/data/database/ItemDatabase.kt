package pe.com.jesus.villa.bankuishchallenge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import pe.com.jesus.villa.bankuishchallenge.BuildConfig
import pe.com.jesus.villa.bankuishchallenge.ui.common.generateRandomString

@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    override fun init(configuration: DatabaseConfiguration) {
        super.init(configuration)
        configuration.context
    }

    companion object {

        private var INSTANCE: ItemDatabase? = null

        fun getInstance(context: Context, nameDb: String): ItemDatabase {
            if(INSTANCE == null) {
                val passDB = "Bankuish"
                val passphrase: ByteArray =
                    if (!BuildConfig.DEBUG) SQLiteDatabase.getBytes(generateRandomString().toCharArray())
                    else SQLiteDatabase.getBytes(passDB.toCharArray())
                val factory = SupportFactory(passphrase)
                //Here it's possible add migrations too
                INSTANCE = Room.databaseBuilder(context, ItemDatabase::class.java, nameDb)
                    .openHelperFactory(factory)
                    .build()
            }
            return INSTANCE!!
        }
    }
}