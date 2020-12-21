package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoodPhotoManager extends PhotoManager {

    Logger logger = Logger.getLogger(FoodPhotoManager.class.getName());

    public FoodPhotoManager() {
            photoTagCollector = FoodPhotoFactory.getInstance().createPhotoTagCollector();
    }

    @Override
    public Photo createPhoto(File file) throws Exception {
        PhotoId id = PhotoId.getNextId();
        FoodPhoto result = null;
        try {
            result = (FoodPhoto) PhotoUtil.createPhoto(file, id);
        }
        catch (Exception e ){
            logger.log(Level.SEVERE,"could not create Foodphoto from File \n"+e.getMessage());
            throw e;
        }
        addPhoto(result);
        return result;
    }

    @Override
    protected Photo getPhotoFromFilter(PhotoFilter filter) {
        PhotoId id = filter.getRandomDisplayablePhotoId();
        FoodPhoto result =null;
        try {
            result= getPhotoFromId(id);
        }
        catch (Exception e)
        {
            logger.severe("Couldnt get photo from filter "+ id.stringValue + "with Exception: "+e.toString());
            throw e;
        }
        while ((result != null) && !result.isVisible()) {
            id = filter.getRandomDisplayablePhotoId();
            result = getPhotoFromId(id);
            if ((result != null) && !result.isVisible()) {
                filter.addProcessedPhoto(result);
            }
        }

        return result;
    }

    @Override
    public FoodPhoto getPhotoFromId(PhotoId id) {
        if (id.isNullId()) {
            return null;
        }

        FoodPhoto result =null;

        try {
            result= doGetPhotoFromId(id);
        }
        catch (Exception e)
        {
            logger.severe("Couldnt get photo from id "+ id.stringValue + "with Exception: "+e.toString());
            throw e;
        }
        if (result == null) {
            try {
                PreparedStatement stmt = getReadingStatement("SELECT * FROM photos WHERE id = ?");
                result = (FoodPhoto) readObject(stmt, id.asInt());
            } catch (SQLException sex) {
                SysLog.logThrowable(sex);
            }
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    @Override
    protected FoodPhoto doGetPhotoFromId(PhotoId id) {
        return (FoodPhoto) photoCache.get(id);
    }

}
