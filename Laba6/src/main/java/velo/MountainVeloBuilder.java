package velo;

public class MountainVeloBuilder extends AbstractVeloBuilder<MountainVelo, MountainVeloBuilder> {
    @Override
    public MountainVelo build() {
        return new MountainVelo(id,date,type,model,price,max_speed);
    }

}