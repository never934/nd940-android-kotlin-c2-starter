package com.udacity.asteroidradar

object Constants {
    const val API_QUERY_DATE_FORMAT = "yyyy-MM-dd"
    const val VIEW_DATE_FORMAT = "dd MMM yyyy"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val NASA_IMAGE_OF_DAY_TYPE = "image"

    // network
        const val CONNECT_TIMEOUT_MILLIS = 3
        const val READ_TIMEOUT_MILLIS = 3
        const val CONTENT_TYPE = "Content-Type"

    // DB
        const val DB_NAME = "asteroids_app_db"

    /** custom view **/
        const val SPARSE_STATE_KEY = "SPARSE_STATE_KEY"
        const val SUPER_STATE_KEY = "SUPER_STATE_KEY"

    /*** Shared Preferences ***/
        // settings file
        const val APP_PREFERENCES = "APP_PREFERENCES"
        //settings id's
        const val KEY_VOLUME_STATE = "KEY_VOLUME_STATE"

}