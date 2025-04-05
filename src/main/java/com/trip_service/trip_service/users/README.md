
### Master Query to give roles to user: 


# Users API
API Listing for Users related apis.

## User Login
```http
  POST /users/login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. user' username |
| `Password` | `string` | **Required**. user' password |

ROLE Required to Access: Anyone on internet

## Register User
```http
  POST /users/create
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userName` | `string` | **Required**. user' username |
| `firstName` | `string` | **Required**. user' first name |
| `lastName` | `string` | **Required**. user' last name |
| `email` | `string` | **Required**. user' email |
| `password` | `string` | **Required**. user' password |
| `phoneNo` | `string` | **Required**. user' phone number |
| `address` | `string` | **Required**. user' address |
| `bookingStationAddress` | `string` | **Required**. user' booking station address (Only Applicable for booking station member) |
| `bookingStationPincode` | `string` | **Required**. user' booking station address (Only Applicable for booking station member) |

ROLE Required to Access: Anyone on internet

## Get Users List
```http
  POST /users/list
```

ROLE Required to Access: [ "ADMIN" ]

## Get Users Info
```http
  POST /users/get-user-info
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. user' username |


ROLE Required to Access: Anyone on internet

## Manipulate Users Role
```http
  POST /users/manipulate-user-role
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `changeType` | `string` | **Required**. 'ADD' or 'REMOVE' Role |
| `username` | `string` | **Required**. user' username |
| `role` | `string` | **Required**. role to ADD or REMOVE |

ROLE Required to Access: ["ADMIN"]
