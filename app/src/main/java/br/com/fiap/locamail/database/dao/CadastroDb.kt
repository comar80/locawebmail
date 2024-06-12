package br.com.fiap.locamail.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.locamail.database.Converters
import br.com.fiap.locamail.model.Cadastro
import br.com.fiap.locamail.model.CaixaComEmails
import br.com.fiap.locamail.model.CaixaEmail
import br.com.fiap.locamail.model.Email

@Database(entities = [Cadastro::class, Email::class, CaixaEmail::class], version = 6)
@TypeConverters(Converters::class)
abstract class CadastroDb: RoomDatabase() {

        abstract fun cadastroDao(): CadastroDao
        abstract fun emailDao(): EmailDao

        companion object{

            private lateinit var instance: CadastroDb

            fun getDatabase(context: Context): CadastroDb{
                if(!::instance.isInitialized){
                    instance = Room
                        .databaseBuilder(
                            context,
                            CadastroDb::class.java,
                            "cadastro_db"
                        )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }


        }


}