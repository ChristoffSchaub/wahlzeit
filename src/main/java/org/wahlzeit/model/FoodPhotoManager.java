package org.wahlzeit.model;

import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FoodPhotoManager extends PhotoManager {


    public FoodPhotoManager() {
        photoTagCollector = FoodPhotoFactory.getInstance().createPhotoTagCollector();
    }

    @Override
    public Photo createPhoto(File file) throws Exception {
        PhotoId id = PhotoId.getNextId();
        FoodPhoto result = (FoodPhoto) PhotoUtil.createPhoto(file, id);
        addPhoto(result);
        return result;
    }

    @Override
    protected Photo getPhotoFromFilter(PhotoFilter filter) {
        PhotoId id = filter.getRandomDisplayablePhotoId();
        FoodPhoto result = getPhotoFromId(id);
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

        FoodPhoto result = doGetPhotoFromId(id);

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
