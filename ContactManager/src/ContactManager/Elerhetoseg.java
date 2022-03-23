package ContactManager;

public class Elerhetoseg {
    private int az, cimid;
    private String elerhetoseg;

    public Elerhetoseg(int az, String elerhetoseg,int cimid) {
        this.az = az;
        this.cimid = cimid;
        this.elerhetoseg = elerhetoseg;
    }

    public int getAz() {
        return az;
    }

    public void setAz(int az) {
        this.az = az;
    }

    public int getCimid() {
        return cimid;
    }

    public void setCimid(int cimid) {
        this.cimid = cimid;
    }

    public String getElerhetoseg() {
        return elerhetoseg;
    }

    public void setElerhetoseg(String elerhetoseg) {
        this.elerhetoseg = elerhetoseg;
    }
    
    
}

