package br.pro.hashi.ensino.desagil.aps.model;

public class NandGate extends Gate {
    private final SignalEmitter[] emitters;

    public NandGate() {
        super("NAND", 2);
        emitters = new SignalEmitter[2];
    }

    @Override
    public boolean read() {
        boolean a = (emitters[0] != null && emitters[0].read());
        boolean b = (emitters[1] != null && emitters[1].read());
        return !(a && b);
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        emitters[inputPin] = emitter;
    }
}
