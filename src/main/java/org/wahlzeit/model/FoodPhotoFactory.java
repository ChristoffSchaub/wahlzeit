package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class FoodPhotoFactory extends PhotoFactory {

    private static final Logger logger = Logger.getLogger(FoodPhotoFactory.class.getName());

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
        FoodPhoto foodPhoto = null;
        try
        {
            foodPhoto = new FoodPhoto();
        }
        catch (Exception e){
            logger.warning("FoodPhoto could not be created. "+e.toString());
            throw e;
        }

        return foodPhoto;
    }

    /**
     *
     */
    @Override
    public FoodPhoto createPhoto(PhotoId id) {
        FoodPhoto foodPhoto = null;
        try
        {
            foodPhoto = new FoodPhoto(id);
        }
        catch (Exception e){
            logger.warning("FoodPhoto could not be created. "+e.toString());
            throw e;
        }

        return foodPhoto;
    }

    /**
     *
     */
    @Override
    public FoodPhoto createPhoto(ResultSet rs) throws SQLException {
        FoodPhoto foodPhoto = null;
        try
        {
            foodPhoto = new FoodPhoto(rs);
        }
        catch (Exception e){
            logger.warning("FoodPhoto could not be created. "+e.toString());
            throw e;
        }

        return foodPhoto;
    }


}
