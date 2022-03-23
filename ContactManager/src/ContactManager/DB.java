package ContactManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class DB {
    
    /**
     * 
     * MySQL csatlakozáshoz szükséges változók
     * 
     */
    final String dbUrl2 = "jdbc:mysql://localhost:3306/nyilvantarto";
    final String user = "root";
    final String pass = "";

    public DB() {
    }

    public void szbeolvas(ObservableList<Szemely> tabla) {
        String s = "SELECT* FROM szemelyek ORDER BY nev ASC";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            tabla.clear();
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                tabla.add(new Szemely(
                        eredmeny.getInt("szemelyid"),
                        eredmeny.getString("nev")
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cbeolvas(ObservableList<Cim> tabla, Integer n) {
        String s = "SELECT*FROM cim WHERE szemelyid=(" + n + ") ORDER BY cim ASC";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            tabla.clear();
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                tabla.add(new Cim(
                        eredmeny.getInt("cimid"),
                        eredmeny.getString("cim"),
                        eredmeny.getInt("szemelyid")
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ebeolvas(ObservableList<Elerhetoseg> tabla, Integer n) {
        String s = "SELECT*FROM elerhetoseg WHERE cimid=" + n + " ORDER BY elerhetoseg ASC;";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            tabla.clear();
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                tabla.add(new Elerhetoseg(
                        eredmeny.getInt("az"),
                        eredmeny.getString("elerhetoseg"),
                        eredmeny.getInt("cimid")
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int njtorol(int id) {
        String s = "DELETE FROM szemelyek WHERE szemelyid=?;";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, id);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public int etorol(int id) {
        String s = "DELETE FROM elerhetoseg WHERE az=?;";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, id);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public int ctorol(int id) {
        String s = "DELETE FROM cim WHERE cimid=?;";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, id);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public int szbeir(String nev) {
        String p = "INSERT INTO szemelyek (nev)"
                + " VALUES (?);";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(p)) {
            ekp.setString(1, nev);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public int cbeir(String nev, int szemelyid) {
        String p = "INSERT INTO cim (cim,szemelyid)"
                + " VALUES (?,?);";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(p)) {
            ekp.setString(1, nev);
            ekp.setInt(2, szemelyid);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public int ebeir(String elerhetoseg, int cimid) {
        String p = "INSERT INTO elerhetoseg (elerhetoseg,cimid)"
                + " VALUES (?,?);";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(p)) {
            ekp.setString(1, elerhetoseg);
            ekp.setInt(2, cimid);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

  public int nevmodosit(String nev, int id) {
        String s = "UPDATE szemelyek SET nev=? WHERE szemelyid=?";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, nev);
            ekp.setInt(2, id);
            
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Hiba", ex.getMessage());
            return 0;
        }  
  }
  
   public int cimmodosit(String cim, int id) {
        String s = "UPDATE cim SET cim=? WHERE cimid=?";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, cim);
            ekp.setInt(2, id);
            
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Hiba", ex.getMessage());
            return 0;
        }  
  }
   
   public int elermodosit(String elerhetoseg, int id) {
        String s = "UPDATE elerhetoseg SET elerhetoseg=? WHERE az=?";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, elerhetoseg);
            ekp.setInt(2, id);
            
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Hiba", ex.getMessage());
            return 0;
        }  
  }
}
