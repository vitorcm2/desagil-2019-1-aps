package br.pro.hashi.ensino.desagil.aps.model;

public interface SignalReceiver {
    void connect(int inputPin, SignalEmitter emitter);
}
