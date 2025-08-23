package com.afs.itech.energySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public record EnergyCrystalData(
        String type,
        int durability,
        int ProductionRateModifier,
        List<Double> DamageChanceModifier
) {
    public static final Random RANDOM = new Random();

    public static List<Double> modify(List<Double> base, List<Double> extra){
        int p = base.size(), q = extra.size(), m = Math.max(p, q);
        List<Double> result = new ArrayList<>();
        for(int i = 0; i < m; i ++){
            double baseV = (i < p? base.get(i): 0);
            double extraV = (i < q? extra.get(i): 0);
            double value = baseV + extraV;
            if(value < 0) value = 0;
            else if(value > 1) value = 1;
            result.add(value);
        }
        for(int endI = m - 1; endI >= 0; endI --){
            if(result.get(endI) == 0) result.remove(endI);
            else break;
        }
        return result;
    }

    public static int getDamage(List<Double> chance){
        double value = RANDOM.nextDouble();
        int i = 0, j = chance.size();
        while(value > 0 && i < chance.size()){
            value -= chance.get(i);
        }
        return i - 1;
    }
}
