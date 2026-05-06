package io.shopwave.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "cached_products")
data class CachedProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val slug: String,
    val price: Double,
    val imageUrl: String,
    val viewedAt: Long = System.currentTimeMillis(),
)

@Dao
interface ProductDao {
    @Query("SELECT * FROM cached_products ORDER BY viewedAt DESC LIMIT 50")
    fun getRecentlyViewed(): Flow<List<CachedProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: CachedProductEntity)

    @Query("DELETE FROM cached_products WHERE viewedAt < :cutoff")
    suspend fun evictOlderThan(cutoff: Long)
}
