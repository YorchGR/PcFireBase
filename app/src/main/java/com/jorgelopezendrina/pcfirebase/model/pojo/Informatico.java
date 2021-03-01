package com.jorgelopezendrina.pcfirebase.model.pojo;

public class Informatico {
    private String imgInfor, nombreInfor, apellInfor, dniInfor, tlfInfor;

    public Informatico() {
    }

    public Informatico( String imgInfor, String nombreInfor, String apellInfor, String dniInfor, String tlfInfor) {
        this.imgInfor = imgInfor;
        this.nombreInfor = nombreInfor;
        this.apellInfor = apellInfor;
        this.dniInfor = dniInfor;
        this.tlfInfor = tlfInfor;
    }

    public String getImgInfor() {
        return imgInfor;
    }

    public void setImgInfor(String imgInfor) {
        this.imgInfor = imgInfor;
    }

    public String getNombreInfor() {
        return nombreInfor;
    }

    public void setNombreInfor(String nombreInfor) {
        this.nombreInfor = nombreInfor;
    }

    public String getApellInfor() {
        return apellInfor;
    }

    public void setApellInfor(String apellInfor) {
        this.apellInfor = apellInfor;
    }

    public String getDniInfor() {
        return dniInfor;
    }

    public void setDniInfor(String dniInfor) {
        this.dniInfor = dniInfor;
    }

    public String getTlfInfor() {
        return tlfInfor;
    }

    public void setTlfInfor(String tlfInfor) {
        this.tlfInfor = tlfInfor;
    }

    @Override
    public String toString() {
        return "Informatico{" +
                ", imgInfor='" + imgInfor + '\'' +
                ", nombreInfor='" + nombreInfor + '\'' +
                ", apellInfor='" + apellInfor + '\'' +
                ", dniInfor='" + dniInfor + '\'' +
                ", tlfInfor='" + tlfInfor + '\'' +
                '}';
    }
}
