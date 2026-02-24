package rvt.StudentRegistration;

public class Students {
    private String vards, uzvards, epasts, personasKods, datums, laiks;

    public Students(String vards, String uzvards, String epasts, String personasKods, String datums, String laiks) {
        this.vards = vards;
        this.uzvards = uzvards;
        this.epasts = epasts;
        this.personasKods = personasKods;
        this.datums = datums;
        this.laiks = laiks;
    }

    public String getName() {
        return vards;
    }

    public void setName(String vards) {
        this.vards = vards;
    }

    public String getSurname() {
        return uzvards;
    }

    public void setSurname(String uzvards) {
        this.uzvards = uzvards;
    }

    public String getEmail() {
        return epasts;
    }

    public String getCode() {
        return personasKods;
    }

    public String getDate() {
        return datums;
    }

    public String getTime() {
        return laiks;
    }

    @Override
    public String toString() {
        return vards + "," + uzvards + "," + epasts + "," + personasKods + "," + datums + "," + laiks; 
    }

    public String toCsv() {
        return vards + "," + uzvards + "," + epasts + "," + personasKods + "," + datums + "," + laiks;
    }
}
