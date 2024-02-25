import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB.ModuleDao
import com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB.TaskDao
import com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB.Module
import com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB.Tasks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Anotación para marcar la clase como una base de datos de Room y especificar las entidades que se incluirán en la base de datos
@Database(entities = [Module::class, Tasks::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Método abstracto para obtener un DAO para interactuar con la tabla `module` en la base de datos
    abstract fun moduleDAO(): ModuleDao

    // Método abstracto para obtener un DAO para interactuar con la tabla `tasks` en la base de datos
    abstract fun tasksDAO(): TaskDao

    companion object {
        // Instancia de la base de datos
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Método para obtener una instancia de la base de datos y se inicializa de forma perezosa para que se cree la primera vez que se accede a ella
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                ).addCallback(object : RoomDatabase.Callback() {
                    // Callback que se ejecutará cuando se cree la base de datos por primera vez
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Insertar algunas asignaturas iniciales en la tabla `module`
                        CoroutineScope(Dispatchers.IO).launch {
                            insertModules(context, getDatabase(context).moduleDAO())
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }

        // Método para insertar algunas asignaturas iniciales en la tabla `module`
        private suspend fun insertModules(context: Context, moduleDao: ModuleDao) {
            val Modulos = listOf(
                Module(moduleName = "Acceso a Datos"),
                Module(moduleName = "PSP"),
                Module(moduleName = "Desarrollo Interfaces"),
                Module(moduleName = "SGE"),
                Module(moduleName = "Ingles tecnico"),
                Module(moduleName = "EIE"),
                Module(moduleName = "PMDM"),
            )
            moduleDao.insertAll(Modulos)
        }
    }
}
