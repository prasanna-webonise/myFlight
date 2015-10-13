package com.webonise.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.File;

public class CacheRegion {

    private String regionName;
    private File image;

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CacheRegion that = (CacheRegion) o;

        return new EqualsBuilder()
                .append(regionName, that.regionName)
                .append(image, that.image)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(regionName)
                .append(image)
                .toHashCode();
    }
}
