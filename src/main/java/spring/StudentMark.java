package spring;

public class StudentMark {
    private String fio;
    private int mark;

    public StudentMark(String fio, int mark) {
        this.fio = fio;
        this.mark = mark;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
