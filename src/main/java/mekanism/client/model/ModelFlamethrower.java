package mekanism.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.Model;

public class ModelFlamethrower extends Model {

    private final ModelRenderer RingButtom;
    private final ModelRenderer RingTop;
    private final ModelRenderer Ring;
    private final ModelRenderer Axle;
    private final ModelRenderer AxleBLeft;
    private final ModelRenderer AxleBRight;
    private final ModelRenderer AxleTRight;
    private final ModelRenderer AxleTLeft;
    private final ModelRenderer Grasp;
    private final ModelRenderer GraspRod;
    private final ModelRenderer SupportCentre;
    private final ModelRenderer SupportFront;
    private final ModelRenderer SupportRear;
    private final ModelRenderer LargeBarrel;
    private final ModelRenderer LargeBarrelDecor;
    private final ModelRenderer LargeBarrelDecor2;
    private final ModelRenderer Barrel;
    private final ModelRenderer BarrelRing;
    private final ModelRenderer BarrelRing2;
    private final ModelRenderer Flame;
    private final ModelRenderer FlameStrut;
    private final ModelRenderer HydrogenDecor;
    private final ModelRenderer Hydrogen;

    public ModelFlamethrower() {
        textureWidth = 64;
        textureHeight = 64;

        RingButtom = new ModelRenderer(this, 19, 14);
        RingButtom.func_228304_a_(0F, 0F, 0F, 3, 1, 3, false);
        RingButtom.setRotationPoint(-2F, 19.5F, 1.5F);
        RingButtom.setTextureSize(64, 64);
        RingButtom.mirror = true;
        setRotation(RingButtom, 0F, 0F, 0F);
        RingTop = new ModelRenderer(this, 19, 14);
        RingTop.func_228304_a_(0F, 0F, 0F, 3, 1, 3, false);
        RingTop.setRotationPoint(-2F, 13.5F, 1.466667F);
        RingTop.setTextureSize(64, 64);
        RingTop.mirror = true;
        setRotation(RingTop, 0F, 0F, 0F);
        Ring = new ModelRenderer(this, 0, 14);
        Ring.func_228304_a_(0F, 0F, 0F, 5, 6, 4, false);
        Ring.setRotationPoint(-3F, 14F, 1F);
        Ring.setTextureSize(64, 64);
        Ring.mirror = true;
        setRotation(Ring, 0F, 0F, 0F);
        Axle = new ModelRenderer(this, 32, 12);
        Axle.func_228304_a_(0F, 0F, 0F, 4, 4, 7, false);
        Axle.setRotationPoint(-2.5F, 15F, -6.5F);
        Axle.setTextureSize(64, 64);
        Axle.mirror = true;
        setRotation(Axle, 0F, 0F, 0F);
        AxleBLeft = new ModelRenderer(this, 0, 25);
        AxleBLeft.func_228304_a_(-0.5F, -0.5F, 0F, 1, 1, 8, false);
        AxleBLeft.setRotationPoint(-2F, 19F, -7F);
        AxleBLeft.setTextureSize(64, 64);
        AxleBLeft.mirror = true;
        setRotation(AxleBLeft, 0F, 0F, 0.2094395F);
        AxleBRight = new ModelRenderer(this, 0, 25);
        AxleBRight.func_228304_a_(-0.5F, -0.5F, 0F, 1, 1, 8, false);
        AxleBRight.setRotationPoint(1F, 19F, -7F);
        AxleBRight.setTextureSize(64, 64);
        AxleBRight.mirror = true;
        setRotation(AxleBRight, 0.0174533F, 0F, -0.2094395F);
        AxleTRight = new ModelRenderer(this, 0, 25);
        AxleTRight.func_228304_a_(-0.5F, -0.5F, 0F, 1, 1, 8, false);
        AxleTRight.setRotationPoint(1F, 15F, -7F);
        AxleTRight.setTextureSize(64, 64);
        AxleTRight.mirror = true;
        setRotation(AxleTRight, 0F, 0F, 0.2094395F);
        AxleTLeft = new ModelRenderer(this, 0, 25);
        AxleTLeft.func_228304_a_(-0.5F, -0.5F, 0F, 1, 1, 8, false);
        AxleTLeft.setRotationPoint(-2F, 15F, -7F);
        AxleTLeft.setTextureSize(64, 64);
        AxleTLeft.mirror = true;
        setRotation(AxleTLeft, 0F, 0F, -0.2094395F);
        Grasp = new ModelRenderer(this, 24, 19);
        Grasp.func_228304_a_(0F, 0F, 0F, 2, 1, 1, false);
        Grasp.setRotationPoint(-1.5F, 13F, -1.1F);
        Grasp.setTextureSize(64, 64);
        Grasp.mirror = true;
        setRotation(Grasp, 0.7807508F, 0F, 0F);
        Grasp.mirror = false;
        GraspRod = new ModelRenderer(this, 19, 19);
        GraspRod.func_228304_a_(0F, 0F, 0F, 1, 3, 1, false);
        GraspRod.setRotationPoint(-1F, 13F, -1F);
        GraspRod.setTextureSize(64, 64);
        GraspRod.mirror = true;
        setRotation(GraspRod, 0.2230717F, 0F, 0F);
        SupportCentre = new ModelRenderer(this, 0, 40);
        SupportCentre.func_228304_a_(0F, 0F, 0F, 2, 1, 6, false);
        SupportCentre.setRotationPoint(-1.5F, 12.4F, 6.6F);
        SupportCentre.setTextureSize(64, 64);
        SupportCentre.mirror = true;
        setRotation(SupportCentre, -0.1115358F, 0F, 0F);
        SupportFront = new ModelRenderer(this, 19, 24);
        SupportFront.func_228304_a_(0F, 0F, 0F, 1, 1, 4, false);
        SupportFront.setRotationPoint(-1F, 13.1F, 12.5F);
        SupportFront.setTextureSize(64, 64);
        SupportFront.mirror = true;
        setRotation(SupportFront, -1.226894F, 0F, 0F);
        SupportRear = new ModelRenderer(this, 0, 35);
        SupportRear.func_228304_a_(0F, 0F, 0F, 3, 1, 3, false);
        SupportRear.setRotationPoint(-2F, 14F, 4F);
        SupportRear.setTextureSize(64, 64);
        SupportRear.mirror = true;
        setRotation(SupportRear, 0.5424979F, 0F, 0F);
        LargeBarrel = new ModelRenderer(this, 19, 48);
        LargeBarrel.func_228304_a_(0F, 0F, 0F, 2, 3, 7, false);
        LargeBarrel.setRotationPoint(-1.5F, 16F, 4F);
        LargeBarrel.setTextureSize(64, 64);
        LargeBarrel.mirror = true;
        setRotation(LargeBarrel, 0F, 0F, 0F);
        LargeBarrelDecor = new ModelRenderer(this, 0, 48);
        LargeBarrelDecor.func_228304_a_(0F, 0F, 0F, 3, 3, 6, false);
        LargeBarrelDecor.setRotationPoint(-2F, 15F, 4F);
        LargeBarrelDecor.setTextureSize(64, 64);
        LargeBarrelDecor.mirror = true;
        setRotation(LargeBarrelDecor, -0.1115358F, 0F, 0F);
        LargeBarrelDecor2 = new ModelRenderer(this, 17, 41);
        LargeBarrelDecor2.func_228304_a_(0F, 0F, 0F, 4, 2, 4, false);
        LargeBarrelDecor2.setRotationPoint(-2.5F, 16F, 4F);
        LargeBarrelDecor2.setTextureSize(64, 64);
        LargeBarrelDecor2.mirror = true;
        setRotation(LargeBarrelDecor2, 0F, 0F, 0F);
        Barrel = new ModelRenderer(this, 19, 30);
        Barrel.func_228304_a_(0F, 0F, 0F, 2, 2, 8, false);
        Barrel.setRotationPoint(-1.5F, 16.5F, 11F);
        Barrel.setTextureSize(64, 64);
        Barrel.mirror = true;
        setRotation(Barrel, 0F, 0F, 0F);
        BarrelRing = new ModelRenderer(this, 30, 25);
        BarrelRing.func_228304_a_(0F, 0F, 0F, 3, 3, 1, false);
        BarrelRing.setRotationPoint(-2F, 16F, 13F);
        BarrelRing.setTextureSize(64, 64);
        BarrelRing.mirror = true;
        setRotation(BarrelRing, 0F, 0F, 0F);
        BarrelRing2 = new ModelRenderer(this, 30, 25);
        BarrelRing2.func_228304_a_(0F, 0F, 0F, 3, 3, 1, false);
        BarrelRing2.setRotationPoint(-2F, 16F, 17F);
        BarrelRing2.setTextureSize(64, 64);
        BarrelRing2.mirror = true;
        setRotation(BarrelRing2, 0F, 0F, 0F);
        Flame = new ModelRenderer(this, 38, 0);
        Flame.func_228304_a_(0F, 0F, 0F, 1, 1, 2, false);
        Flame.setRotationPoint(-1F, 19.5F, 19F);
        Flame.setTextureSize(64, 64);
        Flame.mirror = true;
        setRotation(Flame, 0.7063936F, 0F, 0F);
        FlameStrut = new ModelRenderer(this, 27, 0);
        FlameStrut.func_228304_a_(0F, 0F, 0F, 2, 1, 3, false);
        FlameStrut.setRotationPoint(-1.466667F, 18.5F, 17F);
        FlameStrut.setTextureSize(64, 64);
        FlameStrut.mirror = true;
        setRotation(FlameStrut, -0.2602503F, 0F, 0F);
        HydrogenDecor = new ModelRenderer(this, 27, 5);
        HydrogenDecor.func_228304_a_(0F, 0F, 0F, 3, 1, 5, false);
        HydrogenDecor.setRotationPoint(1.5F, 15.66667F, -4.933333F);
        HydrogenDecor.setTextureSize(64, 64);
        HydrogenDecor.mirror = true;
        setRotation(HydrogenDecor, 0F, 0F, 0.4438713F);
        Hydrogen = new ModelRenderer(this, 0, 0);
        Hydrogen.func_228304_a_(0F, 0F, 0F, 3, 3, 10, false);
        Hydrogen.setRotationPoint(1.5F, 16F, -5.5F);
        Hydrogen.setTextureSize(64, 64);
        Hydrogen.mirror = true;
        setRotation(Hydrogen, 0F, 0F, 0.4438713F);
    }

    public void render(float size) {
        RingButtom.render(size);
        RingTop.render(size);
        Ring.render(size);
        Axle.render(size);
        AxleBLeft.render(size);
        AxleBRight.render(size);
        AxleTRight.render(size);
        AxleTLeft.render(size);
        Grasp.render(size);
        GraspRod.render(size);
        SupportCentre.render(size);
        SupportFront.render(size);
        SupportRear.render(size);
        LargeBarrel.render(size);
        LargeBarrelDecor.render(size);
        LargeBarrelDecor2.render(size);
        Barrel.render(size);
        BarrelRing.render(size);
        BarrelRing2.render(size);
        Flame.render(size);
        FlameStrut.render(size);
        HydrogenDecor.render(size);
        Hydrogen.render(size);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}