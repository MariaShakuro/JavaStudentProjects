package velo;

public class RoadVeloBuilder extends AbstractVeloBuilder<RoadVelo, RoadVeloBuilder> {
    @Override
    public RoadVelo build() {
        return new RoadVelo(id,date,type,model,price,max_speed);
    }
}

