class StudentTelekomunikace extends Student {
    public StudentTelekomunikace(int id, String jmeno, String prijmeni, String datumNarozeni) {
        super(id, jmeno, prijmeni, datumNarozeni);
    }

    @Override
    public String specialSkill() {
        return MorseCodeConverter.toMorse(jmeno + " " + prijmeni);
    }
}
