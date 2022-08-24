package pe.com.jesus.villa.bankuishchallenge.data.database

import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemEntity")
    fun getAllItems(): List<ItemEntity>

    @Query("SELECT * FROM ItemEntity WHERE id = :id")
    fun findItemById(id: Long): ItemEntity

    @Query("SELECT COUNT(id) FROM ItemEntity")
    fun itemCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(itemEntities: List<ItemEntity>)

    @Update
    fun updateItem(itemEntity: ItemEntity)
}