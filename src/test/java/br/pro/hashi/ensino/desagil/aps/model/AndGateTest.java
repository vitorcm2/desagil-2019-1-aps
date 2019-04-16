package br.pro.hashi.ensino.desagil.aps.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AndGateTest {
    private AndGate gate;

    private void build(boolean a, boolean b) {
        gate = new AndGate();
        gate.connect(0, new MockEmitter(a));
        gate.connect(1, new MockEmitter(b));
    }

    @Test
    public void whenReceivingFalseAndFalseShouldReturnFalse() {
        build(false, false);
        Assertions.assertFalse(gate.read());
    }

    @Test
    public void whenReceivingFalseAndTrueShouldReturnFalse() {
        build(false, true);
        Assertions.assertFalse(gate.read());
    }

    @Test
    public void whenReceivingTrueAndFalseShouldReturnFalse() {
        build(true, false);
        Assertions.assertFalse(gate.read());
    }

    @Test
    public void whenReceivingTrueAndTrueShouldReturnTrue() {
        build(true, true);
        Assertions.assertTrue(gate.read());
    }
}
