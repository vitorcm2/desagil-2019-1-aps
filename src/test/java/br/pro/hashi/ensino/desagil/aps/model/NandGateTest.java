package br.pro.hashi.ensino.desagil.aps.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NandGateTest {
    private NandGate gate;

    private void build(boolean a, boolean b) {
        gate = new NandGate();
        gate.connect(0, new MockEmitter(a));
        gate.connect(1, new MockEmitter(b));
    }

    @Test
    public void whenReceivingFalseAndFalseShouldReturnTrue() {
        build(false, false);
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingFalseAndTrueShouldReturnTrue() {
        build(false, true);
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueAndFalseShouldReturnTrue() {
        build(true, false);
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueAndTrueShouldReturnFalse() {
        build(true, true);
        Assertions.assertFalse(gate.read());
    }
}
