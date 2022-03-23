package ContactManager;

public class Cim {
    private int cimid, szemelyid;
    private String cim;

    public Cim(int cimid,String cim, int szemelyid) {
        this.cimid = cimid;
        this.szemelyid = szemelyid;
        this.cim = cim;
    }

    public int getCimid() {
        return cimid;
    }

    public void setCimid(int cimid) {
        this.cimid = cimid;
    }

    public int getSzemelyid() {
        return szemelyid;
    }

    public void setSzemelyid(int szemelyid) {
        this.szemelyid = szemelyid;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }
    
    
}

