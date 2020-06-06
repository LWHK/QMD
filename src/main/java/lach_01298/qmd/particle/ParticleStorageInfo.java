package lach_01298.qmd.particle;

import javax.annotation.Nullable;

/**
 * Wrapper class used to encapsulate information about an IFluidTank.
 */
public final class ParticleStorageInfo
{
    @Nullable
    public final ParticleStack particleStack;
    public final long maxEnergy;
    public final long minEnergy;
    public final int capacity;

    public ParticleStorageInfo(@Nullable ParticleStack particleStack, int maxEnergy, int capacity, int minEnergy)
    {
        this.particleStack = particleStack;
        this.maxEnergy = maxEnergy;
        this.minEnergy = minEnergy;
        this.capacity = capacity;
    }

    public ParticleStorageInfo(IParticleStorage storage)
    {
        this.particleStack = storage.getParticleStack();
        this.maxEnergy = storage.getMaxEnergy();
        this.minEnergy = storage.getMinEnergy();
        this.capacity = storage.getCapacity();
    }
}