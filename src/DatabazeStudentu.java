import java.io.*;
import java.util.*;

class DatabazeStudentu {
    Map<Integer, Student> studenti = new HashMap<>();
    private int dalsiId = 1;

    public void pridejStudenta(Student student) {
        studenti.put(student.getId(), student);
    }

    public Student najdiStudenta(int id) {
        return studenti.get(id);
    }

    public void odeberStudenta(int id) {
        studenti.remove(id);
    }

    public List<Student> getVsechnyStudenty() {
        List<Student> list = new ArrayList<>(studenti.values());
        list.sort(Comparator.comparing(Student::getPrijmeni));
        return list;
    }

    public int getPocetStudentu(Class<?> typ) {
        int count = 0;
        for (Student s : studenti.values()) {
            if (typ.isInstance(s)) count++;
        }
        return count;
    }

    public double getPrumerStudentu(Class<?> typ) {
        double sum = 0;
        int count = 0;
        for (Student s : studenti.values()) {
            if (typ.isInstance(s)) {
                sum += s.getPrumer();
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

    public void ulozStudentaDoSouboru(Student student, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(student);
        }
    }

    public Student nactiStudentaZeSouboru(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Student) in.readObject();
        }
    }

    public int generujId() {
        return dalsiId++;
    }
}
