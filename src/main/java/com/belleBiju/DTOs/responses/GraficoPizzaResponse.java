package com.belleBiju.DTOs.responses;

public class GraficoPizzaResponse {

    private int dinheiro;

    private int pix;

    private int debito;

    private int credito;


    public GraficoPizzaResponse(int dinheiro, int pix, int debito, int credito) {
        this.dinheiro = dinheiro;
        this.pix = pix;
        this.debito = debito;
        this.credito = credito;
    }

    public GraficoPizzaResponse(){}

    public GraficoPizzaResponse toModelGrafico(Object object) {
        GraficoPizzaResponse response = new GraficoPizzaResponse();
        response.setCredito(object.getClass().getModifiers());
        response.setDebito(object.getClass().getModifiers());
        response.setDinheiro(object.getClass().getModifiers());
        response.setPix(object.getClass().getModifiers());

        return response;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public int getPix() {
        return pix;
    }

    public void setPix(int pix) {
        this.pix = pix;
    }

    public int getDebito() {
        return debito;
    }

    public void setDebito(int debito) {
        this.debito = debito;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }
}
