package com.yadaapps.caear.pedidosmaggys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_pedidos.*

class PedidosActivity : AppCompatActivity() {
    lateinit var refi2: DatabaseReference
    lateinit var heroList2:MutableList<BaseDeDatos>
    lateinit var listView2: ListView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        heroList2= mutableListOf()
        listView2=listaPedidos2

        refi2= FirebaseDatabase.getInstance().getReference("Confirmado")

        refi2.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {

                if(p0.exists()){
                    heroList2.clear()
                    for (h in p0.children){
                        val hero = h.getValue(BaseDeDatos::class.java)
                        heroList2.add(hero!!)
                    }

                    val adapter = Datos2Adapter(applicationContext, R.layout.datos2,heroList2)
                    listView2.adapter = adapter

                    for (h in heroList2){
                    }
                }
                else{
                    heroList2.clear()
                    val adapter = Datos2Adapter(applicationContext, R.layout.datos2,heroList2)
                    listView2.adapter = adapter

                    for (h in heroList2){

                    }


                }
            }
        }
        )

        //refi2.child("ta").setValue("no")

        // listView2=listaPedidos2

        // heroList2.add(BaseDeDatos("1","","Sopa + Arroz con Pollo","","",""))

        // val adapter = DatosAdapter(applicationContext, R.layout.datos2,heroList2)
        // listView2.adapter = adapter

    }

}
