import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract class Student implements Serializable {
    protected int id;
    protected String jmeno;
    protected String prijmeni;
    protected String datumNarozeni;
    protected List<Integer> znamky = new ArrayList<>();

    public Student(int id, String jmeno, String prijmeni, String datumNarozeni) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.datumNarozeni = datumNarozeni;
    }

    public void pridejZnamku(int znamka) {
        znamky.add(znamka);
    }

    public double getPrumer() {
        if (znamky.isEmpty()) return 0;
        int sum = 0;
        for (int z : znamky) sum += z;
        return (double) sum / znamky.size();
    }

    public int getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getPrijmeni() { return prijmeni; }
    public String getDatumNarozeni() { return datumNarozeni; }

    public abstract String specialSkill();

    @Override
    public String toString() {
        return "ID: " + id + ", " + jmeno + " " + prijmeni + ", narozen: " + datumNarozeni + ", prumer: " + getPrumer();
    }
}
