package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodPhotoFactory extends PhotoFactory {

    /**
     *
     */
    protected FoodPhotoFactory() {
        super();
    }

    /**
     * @methodtype factory
     */
    @Override
    public FoodPhoto createPhoto() {
        return new FoodPhoto();
    }

    /**
     *
     */
    @Override
    public FoodPhoto createPhoto(PhotoId id) {
        return new FoodPhoto(id);
    }

    /**
     *
     */
    @Override
    public FoodPhoto createPhoto(ResultSet rs) throws SQLException {
        return new FoodPhoto(rs);
    }


}
