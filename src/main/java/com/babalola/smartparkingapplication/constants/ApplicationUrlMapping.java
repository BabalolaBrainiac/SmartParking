package com.babalola.smartparkingapplication.constants;


import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationUrlMapping {
        public static final String ApplicationVersion = "/v1";
        public static final String ADMIN_USER_API = ApplicationVersion + "/api/admin";
        public static final String PARKING_ADDRESS_API = ApplicationVersion + "/api/address";
        public static final String LOCATION_API = ApplicationVersion + "/api/location";
        public static final String PARKING_GARAGE_API = ApplicationVersion + "/api/garage";
        public static final String DRIVER_API = ApplicationVersion + "/api/driver";
        public static final String PARK_OWNER_API = ApplicationVersion + "/api/owner";

        public static final String PARKING_SPACE = ApplicationVersion + "/api/space";






}
