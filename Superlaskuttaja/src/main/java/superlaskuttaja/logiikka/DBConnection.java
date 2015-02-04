/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Augustus58
 */
public class DBConnection {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private final File dbConnData;
    private Scanner lukija;
    private FileWriter kirjoittaja;
    private String addr;
    private String user;
    private String pass;
    private boolean yhdistetty;
    private final String CTLasku;
    private final String CTPankkiviivakoodi;
    private final String CTLaskuttaja;
    private final String CTSuorite;
    private final String CTAsiakas;
    private final String CTAsetukset;

    public DBConnection() {

        CTLasku = "create table IF NOT EXISTS Lasku (\n"
                + "/*Kokonaisluku, > 0.*/\n"
                + "laskunNumero integer NOT NULL,\n"
                + "/*Kokonaisluku, > 0. Voi olla null, koska yleensä ei ole tarvetta viitata aiemmin muodostettuun laskuun.*/\n"
                + "viittausAiempaanLaskuun  integer,\n"
                + "laskuttaja varchar(256) NOT NULL,\n"
                + "laskuttajanVersio integer NOT NULL,\n"
                + "/*Kokonaisluku, >= 0.*/\n"
                + "maksuaika integer NOT NULL,\n"
                + "paivays date NOT NULL,\n"
                + "/*Kokonaisluku, 0 <= viivastyskorko <= 100.*/\n"
                + "viivastyskorko integer NOT NULL,\n"
                + "maksuehto varchar(256) NOT NULL,\n"
                + "lisatiedot varchar(256),\n"
                + "onkoMaksettu boolean NOT NULL,\n"
                + "/*Merkkijono (koostuu numeroista), jonka avulla asiakas voi maksaa laskun helposti. Lisätietoja http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/Pankkiviivakoodi-opas.pdf*/\n"
                + "pankkiviivakoodi varchar(62) NOT NULL,\n"
                + "PRIMARY KEY (laskunNumero),\n"
                + "INDEX (laskuttaja, laskuttajanVersio),\n"
                + "INDEX (pankkiviivakoodi),\n"
                + "FOREIGN KEY (laskuttaja, laskuttajanVersio) REFERENCES Laskuttaja(yrityksenNimi, versio),\n"
                + "FOREIGN KEY (pankkiviivakoodi) REFERENCES Pankkiviivakoodi(pankkiviivakoodi)\n"
                + ");\n"
                + "";

        CTPankkiviivakoodi = "create table IF NOT EXISTS Pankkiviivakoodi (\n"
                + "/*Merkkijono (koostuu numeroista), jonka avulla asiakas voi maksaa laskun helposti. Lisätietoja http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/Pankkiviivakoodi-opas.pdf*/\n"
                + "pankkiviivakoodi varchar(62) NOT NULL,\n"
                + "/*Laskun viitenumero merkkijonoksi muutettuna. Vaatimukset viitteelle mm. http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/kotimaisen_viitteen_rakenneohje.pdf*/\n"
                + "viiteTarkisteella varchar(256) NOT NULL,\n"
                + "/*Positiivinen 18 numeron tarkkuudella oleva desimaaliluku. Kun oletetaan, että laskutettavat summat ovat suuruusluokaltaan alle 10^10, niin summat ovat talletettuina kahdeksan desimaalin tarkkuudella. Käytettävä tietotyyppi parametreineen mahdollistaa tarpeeksi tarkat desimaaliluvut erilaisia laskutoimituksia varten.*/\n"
                + "laskunSumma decimal(13, 4) NOT NULL,\n"
                + "erapaiva date NOT NULL,\n"
                + "PRIMARY KEY (pankkiviivakoodi)\n"
                + ");\n"
                + "";

        CTLaskuttaja = "create table IF NOT EXISTS Laskuttaja (\n"
                + "yrityksenNimi varchar(256) NOT NULL,\n"
                + "versio integer NOT NULL,\n"
                + "alvTunniste varchar(16) NOT NULL,\n"
                + "nimi varchar(256) NOT NULL,\n"
                + "katuosoite varchar(256) NOT NULL,\n"
                + "postinumero varchar(256) NOT NULL,\n"
                + "kaupunki varchar(256) NOT NULL,\n"
                + "puhelinnumero varchar(256) NOT NULL,\n"
                + "sahkopostiOsoite varchar(256) NOT NULL,\n"
                + "/*Kokonaisluku, >= 0.*/\n"
                + "laskujaLahetetty integer NOT NULL,\n"
                + "tilinumero varchar(256) NOT NULL,\n"
                + "tilinumeronPankki varchar(256) NOT NULL,\n"
                + "tilinumeronSwiftBic varchar(256) NOT NULL,\n"
                + "PRIMARY KEY (yrityksenNimi, versio)\n"
                + ");\n"
                + "";

        CTSuorite = "create table IF NOT EXISTS Suorite (\n"
                + "/*Kokonaisluku, > 0.*/\n"
                + "suoritteenNumero integer NOT NULL AUTO_INCREMENT,\n"
                + "/*Kokonaisluku, > 0.*/\n"
                + "lasku integer,\n"
                + "kuvaus varchar(256) NOT NULL,\n"
                + "/*Kokonaisluku, > 0.*/\n"
                + "tilaaja integer NOT NULL,\n"
                + "tilaajanVersio integer NOT NULL,\n"
                + "/*Kokonaisluku, > 0.*/\n"
                + "vastaanottaja integer NOT NULL,\n"
                + "vastaanottajanVersio integer NOT NULL,\n"
                + "/*Positiivinen 18 numeron tarkkuudella oleva desimaaliluku. Kun oletetaan, että sarakkeeseen talletettavat luvut ovat alle 10^10, niin luvut ovat talletettuina kahdeksan desimaalin tarkkuudella. Käytettävä tietotyyppi parametreineen mahdollistaa tarpeeksi tarkat desimaaliluvut erilaisia laskutoimituksia varten.*/\n"
                + "maara decimal(13, 4) NOT NULL,\n"
                + "/*Positiivinen 18 numeron tarkkuudella oleva desimaaliluku. Kun oletetaan, että sarakkeeseen talletettavat luvut ovat alle 10^10, niin luvut ovat talletettuina kahdeksan desimaalin tarkkuudella. Käytettävä tietotyyppi parametreineen mahdollistaa tarpeeksi tarkat desimaaliluvut erilaisia laskutoimituksia varten.*/\n"
                + "maaranYksikot varchar(8) NOT NULL,\n"
                + "/*Positiivinen 18 numeron tarkkuudella oleva desimaaliluku. Kun oletetaan, että sarakkeeseen talletettavat luvut ovat alle 10^10, niin luvut ovat talletettuina kahdeksan desimaalin tarkkuudella. Käytettävä tietotyyppi parametreineen mahdollistaa tarpeeksi tarkat desimaaliluvut erilaisia laskutoimituksia varten.*/\n"
                + "aHintaVeroton decimal(13, 4) NOT NULL,\n"
                + "/*Kokonaisluku, 0 <= alvProsentti<= 100.*/\n"
                + "alvProsentti integer NOT NULL,\n"
                + "/*Tähän sarakkeeseen talletaan mahdollisia suoritteiden aloitusaikoja tai pelkkä päiväys.*/\n"
                + "alkuaika datetime NOT NULL,\n"
                + "/*Tähän sarakkeeseen talletaan mahdollisia suoritteiden lopetusaikoja.*/\n"
                + "loppuaika datetime,\n"
                + "PRIMARY KEY (suoritteenNumero),\n"
                + "INDEX (lasku),\n"
                + "INDEX (tilaaja, tilaajanVersio),\n"
                + "INDEX (vastaanottaja, vastaanottajanVersio),\n"
                + "FOREIGN KEY (lasku) REFERENCES Lasku(laskunNumero),\n"
                + "FOREIGN KEY (tilaaja, tilaajanVersio) REFERENCES Asiakas(asiakasnumero, versio),\n"
                + "FOREIGN KEY (vastaanottaja, vastaanottajanVersio) REFERENCES Asiakas(asiakasnumero, versio)\n"
                + ");\n"
                + "";

        CTAsiakas = "create table IF NOT EXISTS Asiakas (\n"
                + "/*Kokonaisluku, > 0.*/\n"
                + "asiakasnumero integer NOT NULL,\n"
                + "versio integer NOT NULL,\n"
                + "nimi varchar(256) NOT NULL,\n"
                + "katuosoite varchar(256) NOT NULL,\n"
                + "postinumero varchar(256) NOT NULL,\n"
                + "kaupunki varchar(256) NOT NULL,\n"
                + "/*Kokonaisluku, >= 0.*/\n"
                + "laskujaLahetetty integer NOT NULL,\n"
                + "email varchar(256) NOT NULL,\n"
                + "PRIMARY KEY (asiakasnumero, versio)\n"
                + ");\n"
                + "";
        
        CTAsetukset = "create table IF NOT EXISTS Asetukset (\n"
                + "asetuksetID integer NOT NULL,\n"
                + "pdfOletusvientipolku varchar(256),\n"
                + "PRIMARY KEY (asetuksetID)\n"
                + ");\n"
                + "";

        this.rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
        }

        this.dbConnData = new File("dbConnData.txt");

        if (this.dbConnData.exists()) {
            try {
                this.lukija = new Scanner(this.dbConnData);
            } catch (FileNotFoundException ex) {
            }
            try {
                this.addr = this.lukija.nextLine();
                this.user = this.lukija.nextLine();
                this.pass = this.lukija.nextLine();
                lukija.close();
            } catch (NoSuchElementException ex) {
            }
            connect();
        } else {
            try {
                this.dbConnData.createNewFile();
            } catch (IOException ex) {
            }
            this.yhdistetty = false;
        }

    }

    public String getAddr() {
        return addr;
    }

    public String getUser() {
        return user;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public final boolean connect() {
        try {
            con = DriverManager.getConnection("jdbc:" + this.addr, this.user, this.pass);
            st = con.createStatement();
            this.yhdistetty = true;
            st.executeUpdate("use "+ user);
            createTables();
            rs = executeQuery("select * from Asetukset");
            if (!rs.first()) {
                executeUpdate("insert into Asetukset () VALUES (1, NULL)");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            this.yhdistetty = false;
            return false;
        }
    }

    private void createTables() {
        try {
            st.executeUpdate(CTLaskuttaja);
            st.executeUpdate(CTPankkiviivakoodi);
            st.executeUpdate(CTLasku);
            st.executeUpdate(CTAsiakas);
            st.executeUpdate(CTSuorite);
            st.executeUpdate(CTAsetukset);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }
    }

    public void saveConnData() {
        try {
            this.kirjoittaja = new FileWriter(this.dbConnData);
        } catch (IOException ex) {
        }
        try {
            kirjoittaja.write(addr + "\n");
            kirjoittaja.write(user + "\n");
            kirjoittaja.write(pass);
            kirjoittaja.close();
        } catch (IOException ex) {
        }
    }

    public ResultSet executeQuery(String kysely) {
        try {
            rs = st.executeQuery(kysely);
        } catch (SQLException sQLException) {
            System.out.println("Error: " + sQLException);
            return null;
        }
        return rs;
    }
    
    public int executeUpdate(String sql) {
        int ret;
        try {
            ret = st.executeUpdate(sql);
            return ret;
        } catch (SQLException sQLException) {
            System.out.println("Error: " + sQLException);
            return -1;
        }
    }

    public boolean isYhdistetty() {
        return yhdistetty;
    }
}
