/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import java.sql.*;
import java.util.logging.Logger;

import org.wahlzeit.annotations.PatternInstance;
import org.wahlzeit.services.*;

/**
 * An Abstract Factory for creating photos and related objects.
 */
@PatternInstance(
        patternName = "Abstract Factory",
        participants = {
                "PhotoFactory", "FoodPhotoFactory"
        }
)
public class PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static PhotoFactory instance = null;
    private static final Logger logger = Logger.getLogger(PhotoFactory.class.getName());

    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic PhotoFactory");
            try {
                setInstance(new FoodPhotoFactory());
            } catch (Exception e) {
                logger.severe("settingInstance of FoodPhotFactory went ront \n" + e.getMessage());
                throw e;
            }
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(PhotoFactory photoFactory) throws IllegalStateException {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize PhotoFactory twice");
        }

        instance = photoFactory;
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     *
     */
    protected PhotoFactory() {
        // do nothing
    }

    /**
     * @methodtype factory
     */
    public Photo createPhoto() {
        Photo photo = null;
        try {
            photo = new Photo();
        } catch (Exception e) {
            logger.severe("settingInstance of FoodPhotFactory went ront \n" + e.getMessage());
            throw e;
        }
        return photo;
    }

    /**
     *
     */
    public Photo createPhoto(PhotoId id) {
        Photo photo = null;
        try {
            photo = new Photo(id);
        } catch (Exception e) {
            logger.severe("settingInstance of FoodPhotFactory went ront \n" + e.getMessage());
            throw e;
        }
        return photo;
    }

    /**
     *
     */
    public Photo createPhoto(ResultSet rs) throws SQLException {
        Photo photo = null;
        try {
            photo = new Photo(rs);
        } catch (Exception e) {
            logger.severe("settingInstance of FoodPhotFactory went ront \n" + e.getMessage());
            throw e;
        }
        return photo;
    }

    /**
     *
     */
    public PhotoFilter createPhotoFilter() {
        return new PhotoFilter();
    }

    /**
     *
     */
    public PhotoTagCollector createPhotoTagCollector() {
        return new PhotoTagCollector();
    }

}
