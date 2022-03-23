package ContactManager;

public class Szemely {
    private int szemelyid;
    private String nev;

    public Szemely(int szemelyid, String nev) {
        this.szemelyid = szemelyid;
        this.nev = nev;
    }

    public int getSzemelyid() {
        return szemelyid;
    }

    public void setSzemelyid(int szemelyid) {
        this.szemelyid = szemelyid;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }
    
    
}

