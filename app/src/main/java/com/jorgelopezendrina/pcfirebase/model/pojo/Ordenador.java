package com.jorgelopezendrina.pcfirebase.model.pojo;

public class Ordenador {

    private String dniInfor, marcaPc, modeloPc, idPc;

    public Ordenador() {
    }

    public Ordenador(String dniInfor, String marcaPc, String modeloPc, String idPc) {
        this.dniInfor = dniInfor;
        this.marcaPc = marcaPc;
        this.modeloPc = modeloPc;
        this.idPc = idPc;
    }

    public String getDniInfor() {
        return dniInfor;
    }

    public void setDniInfor(String dniInfor) {
        this.dniInfor = dniInfor;
    }

    public String getMarcaPc() {
        return marcaPc;
    }

    public void setMarcaPc(String marcaPc) {
        this.marcaPc = marcaPc;
    }

    public String getModeloPc() {
        return modeloPc;
    }

    public void setModeloPc(String modeloPc) {
        this.modeloPc = modeloPc;
    }

    public String getIdPc() {
        return idPc;
    }

    public void setIdPc(String idPc) {
        this.idPc = idPc;
    }

    @Override
    public String toString() {
        return "Ordenador{" +
                "dniInfor='" + dniInfor + '\'' +
                ", marcaPc='" + marcaPc + '\'' +
                ", modeloPc='" + modeloPc + '\'' +
                ", idPc='" + idPc + '\'' +
                '}';
    }
}
