
# Vehicle API
API Lising 

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


# Route API

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


## Get All Routes
```http
  POST /route/create
```

# Trip API
