package com.yadaapps.caear.pedidosmaggys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var editText:EditText
    lateinit var edTelephone:EditText
    lateinit var button: Button

    lateinit var tvPrecioTotal:TextView

    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button

    lateinit var image1: ImageView
    lateinit var image2: ImageView
    lateinit var image3: ImageView
    lateinit var image4: ImageView
    lateinit var image5: ImageView

    lateinit var checkBox1: CheckBox
    lateinit var checkBox2: CheckBox
    lateinit var checkBox3: CheckBox
    lateinit var checkBox4: CheckBox
    lateinit var checkBox5: CheckBox

    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var editText3: EditText
    lateinit var editText4: EditText
    lateinit var editText5: EditText

    var precioTotal = 0

    lateinit var ref : DatabaseReference
    lateinit var refi:DatabaseReference
    lateinit var refina:DatabaseReference
    lateinit var heroList:MutableList<BaseDeDatos>

    lateinit var imagenList:MutableList<Upload>
    lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//***********************************Instanciamos objetos*******************************************************************
        ref = FirebaseDatabase.getInstance().getReference("Pedidos")
        refi=FirebaseDatabase.getInstance().getReference("Confirmado")
        refina = FirebaseDatabase.getInstance().getReference("usuarios")


        heroList= mutableListOf()
        imagenList= mutableListOf()
        editText=etNombre
        edTelephone=etTel
        button=btnEnviar

        tvPrecioTotal = tvPrecio

        btn1=btn_agregar1
        btn2=btn_agregar2
        btn3=btn_agregar3
        btn4=btn_agregar4
        btn5=btn_agregar5

        image1=imageView1
        image2=imageView2
        image3=imageView3
        image4=imageView4
        image5=imageView5

        checkBox1=cb_llevar1
        checkBox2=cb_llevar2
        checkBox3=cb_llevar3
        checkBox4=cb_llevar4
        checkBox5=cb_llevar5

        editText1=et_cant1
        editText2=et_cant2
        editText3=et_cant3
        editText4=et_cant4
        editText5=et_cant5

        listView=listaView



//***********************************Añadimos metodos*******************************************************************
       // Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/prueba-734f3.appspot.com/o/imagen1.jpg?alt=media&token=7f7d1501-5068-47e0-bc14-352613f8b386").into(image1)


        btn1.setOnClickListener {

            if(et_cant1.text.isNotEmpty()){
                AgregarPedido1()
            }
            else{
                Toast.makeText(applicationContext,"el pedido esta vacio, ingrese una cantidad",Toast.LENGTH_LONG).show()
            }
        }
        btn2.setOnClickListener {
            AgregarPedido2()
        }
        btn3.setOnClickListener {
            AgregarPedido3()
        }
        btn4.setOnClickListener {
            AgregarPedido4()
        }
        btn5.setOnClickListener {
            AgregarPedido5()
        }

        button.setOnClickListener {
            apretaBtn()


        }
///////////IMAGENES/////////////////////
        var miimagen="https://www.google.com.ar/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
        refina.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for (h in p0.children){
                        val uplo = h.getValue(Upload::class.java)
                        imagenList.add(uplo!!)
                    }
                    for (h in imagenList){

                        Picasso.get().load(h.mimageUrl).into(image1)}
            }

               // Picasso.get().load(miimagen).into(image1)
            }
        })
        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0.exists()){
                    heroList.clear()
                    for (h in p0.children){
                        val hero = h.getValue(BaseDeDatos::class.java)
                        heroList.add(hero!!)
                    }

                    val adapter = DatosAdapter(applicationContext, R.layout.datos,heroList)
                    listView.adapter = adapter
                    precioTotal = 0
                    for (h in heroList){

                        precioTotal =precioTotal+ (h.cant.toInt() * h.precio.toInt())
                    }
                    tvPrecioTotal.text=precioTotal.toString()
                }
                else{
                    heroList.clear()
                    val adapter = DatosAdapter(applicationContext, R.layout.datos,heroList)
                    listView.adapter = adapter
                    precioTotal = 0
                    for (h in heroList){

                        precioTotal =precioTotal + (h.cant.toInt() * h.precio.toInt())
                    }
                    tvPrecioTotal.text=precioTotal.toString()

                }
            }
        }
        )
    }
    private fun AgregarPedido5() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun AgregarPedido4() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun AgregarPedido3() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun AgregarPedido2() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun AgregarPedido1() {

        var paraLlevar: String
        if (checkBox1.isChecked){
            paraLlevar="Para LLevar"}
        else {
            paraLlevar = ""
        }
        var cant=et_cant1.text.toString().trim()

        val heroId = ref.push().key.toString()
        //heroList.add(BaseDeDatos("1","","Sopa + Arroz con Pollo",paraLlevar,cant))
        // val adapter = DatosAdapter(applicationContext, R.layout.datos,heroList)
        //listView.adapter = adapter

        val hero = BaseDeDatos(heroId,"","Sopa + Arroz con Pollo",paraLlevar,cant,"150","")

        ref.child(heroId).setValue(hero).addOnCompleteListener(){
            Toast.makeText(applicationContext,"hola",Toast.LENGTH_LONG).show()

            precioTotal = 0
            for (h in heroList){
                precioTotal =precioTotal+ (h.cant.toInt() * h.precio.toInt())
            }
            tvPrecioTotal.text=precioTotal.toString()
        }
    }
    private fun  apretaBtn() {

        var numCliente = editText.text.toString().trim()
        var telefono= edTelephone.text.toString().trim()

        for (h in heroList){
            val heroId = ref.push().key.toString()
            val hero = BaseDeDatos(numCliente,h.cliente,"Sopa + Arroz con Pollo",h.llevar,h.cant,h.precio,telefono)
            refi.child(heroId).setValue(hero)
            //cambio

        }
        ref.removeValue()
    }
}
