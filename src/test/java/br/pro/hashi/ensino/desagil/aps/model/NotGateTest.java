package br.pro.hashi.ensino.desagil.aps.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotGateTest {
    private NotGate gate;

    private void build(boolean signal) {
        gate = new NotGate();
        gate.connect(0, new MockEmitter(signal));
    }

    @Test
    public void whenReceivingFalseShouldReturnTrue() {
        build(false);
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueShouldReturnFalse() {
        build(true);
        Assertions.assertFalse(gate.read());
    }
}
