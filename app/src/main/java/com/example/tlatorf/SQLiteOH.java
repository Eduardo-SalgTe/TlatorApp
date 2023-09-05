package com.example.tlatorf;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class SQLiteOH extends SQLiteOpenHelper{
    public SQLiteOH(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dbTP) {
        dbTP.execSQL("CREATE TABLE ChTor (ChTor_id VARCHAR(20), ChTor_v VARCHAR(5))");
        dbTP.execSQL("CREATE TABLE LTTORNEOS (TORNEO_ID VARCHAR(20), NOMBRE VARCHAR(40), APERTURA VARCHAR(40))");

        dbTP.execSQL("CREATE TABLE ChEst (ChEst_id VARCHAR(20), ChEst_v VARCHAR(5))");
        dbTP.execSQL("CREATE TABLE LTESTADISTICAS (ESTA_ID VARCHAR(20), EQUIPO_FK VARCHAR(20), PUNTOS VARCHAR(5), JUGADOS VARCHAR(5),GANADOS VARCHAR(5),EMPATES VARCHAR(5),PERDIDOS VARCHAR(5), TORNEO_FK VARCHAR(20))");
        // SELECT * FROM ESTADISTICAS WHERE TORNEO_FK = "TEST";
        // MATRIZ +- ROW;
        // DELETE FROM LTESTADISTICAS WHERE ESTA_ID != "";
        // INSERT INTO LTESTADISTICAS VALUES (MATRIZ);

        dbTP.execSQL("CREATE TABLE ChPart (ChPart_id VARCHAR(20), ChPart_v VARCHAR(5))");
        dbTP.execSQL("CREATE TABLE LTPARTIDOS (PARTIDO_ID VARCHAR (20), EQUIPO_A VARCHAR(20), EQUIPO_B VARCHAR(20), HORA VARCHAR(10), LUGAR VARCHAR(40), TORNEO_FK VARCHAR(20))");

        //dbTP.execSQL("CREATE TABLE Ch");

        //dbTP.execSQL("CREATE TABLE ChNews (ChNews_v VARCHAR(5), ChNews_id VARCHAR(20))");
        dbTP.execSQL("CREATE TABLE APPVERSION (VERSION VARCHAR(3))");
        /*dbTP.execSQL("CREATE TABLE chofer (nombre VARCHAR(40), numero INT(5), apep VARCHAR(40), apem VARCHAR(40), edad INT(4), curp VARCHAR(20) NOT NULL PRIMARY KEY, localidad VARCHAR(40), direccion VARCHAR(40), telef1 INT(20), telf2 INT(20), licencia VARCHAR(40), tipLice VARCHAR(30), FOREIGN KEY(numero) REFERENCES vehiculo(numero))");
        dbTP.execSQL("CREATE TABLE vehiculo (numero int(5) NOT NULL PRIMARY KEY, placas varchar(15) NOT NULL, marca varchar(30), modelo varchar(40), descrip varchar(50))");
        dbTP.execSQL("CREATE TABLE pago (id varchar(35) NOT NULL PRIMARY KEY, chofer varchar(20), comentarios varchar(50), ano int(9), mes int(9), pago1 DECIMAL(10,2), pago2 DECIMAL(10,2), pago3 DECIMAL(10,2), pago4 DECIMAL(10,2), pago5 DECIMAL(10,2), FOREIGN KEY (chofer) REFERENCES chofer(curp))");
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
