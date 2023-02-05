package dev.jwall64.midnightlib.math;

import dev.jwall64.mdcfg.serializer.InlineSerializer;
import dev.jwall64.mdcfg.serializer.ObjectSerializer;
import dev.jwall64.mdcfg.serializer.Serializer;

import java.util.Objects;

public class Region {

    private final Vec3d lower;
    private final Vec3d extent;

    public Region(Vec3d lower, Vec3d extent) {
        this.lower = lower;
        this.extent = extent;
    }

    public Vec3d getLowerBound() {
        return lower;
    }

    public Vec3d getUpperBound() {
        return lower.add(extent);
    }

    public Vec3d getExtent() {
        return extent;
    }

    public boolean isWithin(Vec3d vector) {

        Vec3d upper = getUpperBound();

        return vector.getX() >= lower.getX() && vector.getX() < upper.getX() &&
               vector.getY() >= lower.getY() && vector.getY() < upper.getY() &&
               vector.getZ() >= lower.getZ() && vector.getZ() < upper.getZ();

    }

    public boolean isWithin(Vec3i vector) {

        return isWithin(new Vec3d(vector.getX(), vector.getY(), vector.getZ()));
    }

    public String toString() {
        return lower.toString() + ";" + extent.toString();
    }

    public static Region parse(String s) {

        String[] ss = s.split(";");

        Vec3d lower = Vec3d.parse(ss[0]);
        Vec3d extent = Vec3d.parse(ss[1]);

        return new Region(lower, extent);
    }

    public static final Serializer<Region> SERIALIZER = InlineSerializer.of(Objects::toString, Region::parse).or(
            ObjectSerializer.create(
                    Vec3d.SERIALIZER.entry("position", Region::getLowerBound),
                    Vec3d.SERIALIZER.entry("extent", Region::getExtent),
                    Region::new
            ));

}
