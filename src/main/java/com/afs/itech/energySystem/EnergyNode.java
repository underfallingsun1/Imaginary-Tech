package com.afs.itech.energySystem;

public interface EnergyNode {
    boolean consumeEnergy(int value, double lossFactor);
}
