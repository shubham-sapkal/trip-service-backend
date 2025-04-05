
# Vehicle API
API Listing for Vehicle related apis.

## Register Vehicle
```http
  POST /vehicle/register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `registrationNumber` | `string` |  Vehicle Registeration Number |
| `ownerUserName` | `string` | Owner Of Vehicle |
| `noOfSeats` | `string` | No. Of Seats in Vehicle |
| `pricePerKm` | `number` | Price Per Km |

ROLE Required to Access:  [  ]

# Route API
API Listing for Route Related apis.

## Create Route
```http
  POST /route/create
```

| Parameter | Type     | Description                                             |
| :-------- |:---------|:--------------------------------------------------------|
| `userId` | `string` | userId Of Route Creater                                 |
| `routeName` | `string` | Route Name                                              |
| `routeOrigin` | `string` | Start Point Of Route                                    |
| `routeDestination` | `string` | Destination Point Of Route                              |
| `routeStops` | `array`  | Route Stops Array of Objects {locationName, cityName, pinCode} |

ROLE Required to Access: [ "ADMIN", "TRIP_ADMIN", "ROUTE_ADMIN"  ]

## Get All Routes
```http
  POST /route/all
```

ROLE Required to Access: [ "ADMIN" ]

# Trip API
API Listing for Trip Related apis

## Create Trip
```http
  POST /trip/create
```

| Parameter | Type      | Description                                    |
| :-------- |:----------|:-----------------------------------------------|
| `createdBy` | `string`  | creater of trip ( based on logedin user )      |
| `vehicleDetails` | `string`  | id of Vehicle used for trip                    |
| `tripRoute` | `string`  | id of trip route from where trip will be gone. |
| `driver` | `string`  | username of vehicle driver                     |
| `tripStartDate` | `string`  | Trip Start Date in YYYY-MM-DD HH:MM:SS format  |
| `tripEndDate` | `string`  | Trip End Date in YYYY-MM-DD HH:MM:SS format    |
| `isReturnTrip` | `boolean` | Is Trip return trip                            |

ROLE Required to Access: [ "ADMIN", "TRIP_ADMIN", "TRIP_CREATER" ]

## Get All Trips
```http
  GET /trip/all
```

ROLE Required to Access: Anyone on the internet

## Get Trip Details by their id
```http 
  POST /trip/trip-details/:trip-id
```

ROLE Required to Access: Anyone on the internet