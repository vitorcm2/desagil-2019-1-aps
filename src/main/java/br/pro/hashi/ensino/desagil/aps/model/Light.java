package br.pro.hashi.ensino.desagil.aps.model;

public class Light implements SignalReceiver {
    private int r;
    private int g;
    private int b;
    private SignalEmitter emitter;

    public Light() {
        r = 0;
        g = 0;
        b = 0;
        emitter = null;
    }

    public int getR() {
        if (emitter != null && emitter.read()) {
            return r;
        }
        return 0;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        if (emitter != null && emitter.read()) {
            return g;
        }
        return 0;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        if (emitter != null && emitter.read()) {
            return b;
        }
        return 0;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin != 0) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        this.emitter = emitter;
    }
}
